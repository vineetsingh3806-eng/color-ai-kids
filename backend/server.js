const express = require('express');
const cors = require('cors');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 8080;

// Enable CORS and generous JSON limit
app.use(cors());
app.use(express.json({ limit: '10mb' }));

// Blocklist for child safety & copyright protection
const BLOCKED_WORDS = [
  // Violence / Weapons / Inappropriate
  'gun', 'rifle', 'pistol', 'sword', 'knife', 'dagger', 'blood', 'bloody', 'gore',
  'kill', 'death', 'dead', 'corpse', 'fight', 'weapon', 'grenade', 'bomb',
  'scary', 'horror', 'spooky', 'demon', 'devil', 'monster attack',
  'sexy', 'naked', 'nude', 'drugs', 'beer', 'alcohol', 'cigarette', 'smoking',
  // Copyrighted / Trademarked franchises
  'mickey mouse', 'donald duck', 'disney', 'marvel', 'spiderman', 'batman', 'superman',
  'pokemon', 'pikachu', 'barbie', 'mario', 'sonic', 'elsa', 'frozen', 'star wars',
  'harry potter', 'peppa pig', 'paw patrol', 'minion', 'minecraft'
];

/**
 * Validates text for child safety and copyright
 */
function validateChildSafety(text) {
  if (!text || typeof text !== 'string') return { valid: true };
  const lower = text.toLowerCase();
  for (const word of BLOCKED_WORDS) {
    // Regex matching word boundary
    const regex = new RegExp(`\\b${word}\\b`, 'i');
    if (regex.test(lower)) {
      return { valid: false, reason: `Word "${word}" is not allowed in child-safe coloring mode.` };
    }
  }
  return { valid: true };
}

/**
 * Builds child-friendly coloring prompt enforcing black and white thick line art
 */
function buildColoringPrompt(character, location, action, customPrompt) {
  const subject = customPrompt && customPrompt.trim().length > 0
    ? customPrompt.trim()
    : `cute cartoon ${character || 'friendly animal'} ${action || 'playing'} in a ${location || 'park'}`;

  return `Children's coloring book page: A ${subject}. ` +
    `Strict drawing specifications: ` +
    `- Pure white background (#FFFFFF) with crisp, high-contrast black line art only. ` +
    `- Clean, bold, thick black outlines with large enclosed areas ideal for coloring inside the lines. ` +
    `- Simple, charming cartoon style designed specifically for young kids (ages 3 to 8). ` +
    `- Absolutely NO color, NO shading, NO gray tones, NO gradients, NO shadows, NO texture fills, NO cross-hatching. ` +
    `- Absolutely NO text, NO words, NO letters, NO numbers, NO logos, NO watermarks, NO signatures. ` +
    `- Wholesome, friendly, cheerful, and 100% child-safe.`;
}

/**
 * Extracts base64 image from Gemini generateContent or Imagen predict responses
 */
function extractImageBase64(data) {
  if (!data) return null;

  // 1. Current Gemini generateContent API format:
  // candidates[0].content.parts[...].inlineData.data
  const candidates = data.candidates || [];
  for (const candidate of candidates) {
    const parts = candidate?.content?.parts || [];
    for (const part of parts) {
      const inlineData = part?.inlineData || part?.inline_data;
      if (inlineData?.data && typeof inlineData.data === 'string' && inlineData.data.length > 50) {
        return inlineData.data;
      }
    }
  }

  // 2. Fallback check for predict format if an Imagen model is configured
  if (Array.isArray(data.predictions) && data.predictions[0]) {
    const p = data.predictions[0];
    if (p.bytesBase64Encoded && typeof p.bytesBase64Encoded === 'string') {
      return p.bytesBase64Encoded;
    }
  }

  return null;
}

// Health Check Endpoint
app.get(['/', '/health'], (req, res) => {
  const model = process.env.GEMINI_MODEL || 'gemini-3.1-flash-image-preview';
  res.json({
    status: 'ok',
    service: 'ColorAI Kids Backend',
    model: model,
    api: 'Gemini generateContent API',
    timestamp: new Date().toISOString()
  });
});

/**
 * POST /api/generate-coloring-page
 * Contract matching ColorAI Kids Android client
 */
app.post(['/api/generate-coloring-page', '/generate'], async (req, res) => {
  const apiKey = process.env.GEMINI_API_KEY;

  if (!apiKey || apiKey.trim() === '' || apiKey.trim() === 'YOUR_GEMINI_API_KEY') {
    console.error('[Backend] GEMINI_API_KEY is not configured on the server.');
    return res.status(500).json({
      error: 'AI creation is currently unavailable. Server API key is not configured.',
      isQuotaExceeded: false
    });
  }

  const { character, location, action, prompt } = req.body || {};

  // Child-safety and copyright check on incoming inputs
  const combinedInput = `${character || ''} ${location || ''} ${action || ''} ${prompt || ''}`;
  const safetyCheck = validateChildSafety(combinedInput);
  if (!safetyCheck.valid) {
    console.warn(`[Backend] Input blocked by safety filter: ${safetyCheck.reason}`);
    return res.status(400).json({
      error: 'Please choose a friendly, fun, and original drawing idea without copyrighted characters!',
      isQuotaExceeded: false
    });
  }

  const synthesizedPrompt = buildColoringPrompt(character, location, action, prompt);
  console.log(`[Backend] Generating coloring page for: Character="${character}", Location="${location}", Action="${action}"`);

  // Default to Gemini 3.1 Flash Image preview model
  const model = process.env.GEMINI_MODEL || 'gemini-3.1-flash-image-preview';
  const endpoint = `https://generativelanguage.googleapis.com/v1beta/models/${model}:generateContent?key=${apiKey.trim()}`;

  // Current Google Gemini generateContent payload for image generation
  const payload = {
    contents: [
      {
        parts: [
          {
            text: synthesizedPrompt
          }
        ]
      }
    ],
    generationConfig: {
      responseModalities: ['TEXT', 'IMAGE'],
      imageConfig: {
        aspectRatio: '1:1',
        imageSize: '1K'
      }
    }
  };

  // Fetch with configurable timeout (default 55 seconds)
  const timeoutMs = parseInt(process.env.GEMINI_TIMEOUT_MS, 10) || 55000;

  try {
    const response = await fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload),
      signal: AbortSignal.timeout(timeoutMs)
    });

    const status = response.status;
    console.log(`[Backend] Gemini API responded with HTTP ${status}`);

    if (status === 429) {
      console.warn('[Backend] Gemini quota limit exceeded (HTTP 429).');
      return res.status(429).json({
        error: 'AI Magic is temporarily unavailable because the AI generation limit has been reached. Please try again later.',
        isQuotaExceeded: true
      });
    }

    if (!response.ok) {
      const errorText = await response.text();
      console.error(`[Backend] Upstream Gemini API error (${status}):`, errorText);

      let parsedError = null;
      try {
        parsedError = JSON.parse(errorText);
      } catch (e) {
        // ignore parse error
      }

      const errorMessage = parsedError?.error?.message || `Upstream generation error (${status})`;
      const isQuota = errorMessage.toLowerCase().includes('quota') || status === 429;

      if (isQuota) {
        return res.status(429).json({
          error: 'AI Magic is temporarily unavailable because the AI generation limit has been reached. Please try again later.',
          isQuotaExceeded: true
        });
      }

      return res.status(status >= 400 && status < 500 ? status : 500).json({
        error: 'AI creation is currently unavailable. Please try another fun drawing idea!',
        isQuotaExceeded: false
      });
    }

    const data = await response.json();
    const imageBase64 = extractImageBase64(data);

    if (!imageBase64) {
      console.error('[Backend] Gemini response did not contain image inlineData:', JSON.stringify(data).slice(0, 300));
      return res.status(502).json({
        error: 'AI creation is currently unavailable. No image was generated.',
        isQuotaExceeded: false
      });
    }

    console.log(`[Backend] Successfully extracted generated image (${imageBase64.length} base64 chars).`);
    return res.status(200).json({
      image: imageBase64
    });

  } catch (err) {
    if (err.name === 'TimeoutError' || err.name === 'AbortError') {
      console.error('[Backend] Upstream Gemini request timed out:', err.message);
      return res.status(504).json({
        error: 'AI creation took too long. Please try again with a simpler drawing idea!',
        isQuotaExceeded: false
      });
    }

    console.error('[Backend] Network or runtime exception during generation:', err.message);
    return res.status(500).json({
      error: 'AI creation is currently unavailable.',
      isQuotaExceeded: false
    });
  }
});

// Export helper functions for automated testing
module.exports = {
  app,
  validateChildSafety,
  buildColoringPrompt,
  extractImageBase64
};

// Start server if run directly
if (require.main === module) {
  const server = app.listen(PORT, '0.0.0.0', () => {
    console.log(`[Backend] ColorAI Kids backend service listening on 0.0.0.0:${PORT}`);
  });

  // Graceful shutdown handling for Cloud Run container lifecycle
  process.on('SIGTERM', () => {
    console.log('[Backend] SIGTERM signal received: closing HTTP server gracefully');
    server.close(() => {
      console.log('[Backend] HTTP server closed');
      process.exit(0);
    });
  });

  process.on('SIGINT', () => {
    console.log('[Backend] SIGINT signal received: closing HTTP server gracefully');
    server.close(() => {
      console.log('[Backend] HTTP server closed');
      process.exit(0);
    });
  });
}
