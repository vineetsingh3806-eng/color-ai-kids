const test = require('node:test');
const assert = require('node:assert/strict');
const {
  validateChildSafety,
  buildColoringPrompt,
  extractImageBase64
} = require('../server');

test('validateChildSafety passes safe inputs', () => {
  assert.equal(validateChildSafety('cute little dinosaur playing in jungle').valid, true);
  assert.equal(validateChildSafety('astronaut dancing on the moon').valid, true);
  assert.equal(validateChildSafety('princess having a tea party in fairy garden').valid, true);
});

test('validateChildSafety blocks inappropriate words', () => {
  assert.equal(validateChildSafety('a scary monster with a gun').valid, false);
  assert.equal(validateChildSafety('a warrior fighting with a sword').valid, false);
  assert.equal(validateChildSafety('someone drinking beer').valid, false);
});

test('validateChildSafety blocks copyrighted characters', () => {
  assert.equal(validateChildSafety('pikachu casting thunderbolt').valid, false);
  assert.equal(validateChildSafety('mickey mouse at disneyland').valid, false);
  assert.equal(validateChildSafety('spiderman crawling on wall').valid, false);
  assert.equal(validateChildSafety('barbie in dreamhouse').valid, false);
});

test('buildColoringPrompt generates strict black and white coloring requirements', () => {
  const prompt = buildColoringPrompt('Friendly Puppy', 'Sunny Park', 'Running');
  assert.match(prompt, /Children's coloring book page/);
  assert.match(prompt, /Pure white background/);
  assert.match(prompt, /thick black outlines/i);
  assert.match(prompt, /NO shading/i);
  assert.match(prompt, /NO text/i);
  assert.match(prompt, /NO logos/i);
});

test('extractImageBase64 extracts from Gemini generateContent inlineData', () => {
  const mockGeminiResponse = {
    candidates: [
      {
        content: {
          parts: [
            { text: 'Here is your coloring page' },
            {
              inlineData: {
                mimeType: 'image/png',
                data: 'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII='
              }
            }
          ]
        }
      }
    ]
  };

  const image = extractImageBase64(mockGeminiResponse);
  assert.equal(image, 'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII=');
});

test('extractImageBase64 handles snake_case inline_data', () => {
  const mockSnakeCaseResponse = {
    candidates: [
      {
        content: {
          parts: [
            {
              inline_data: {
                mimeType: 'image/png',
                data: 'SNAKE_CASE_BASE64_DATA_MOCK_TEST_VALUE_FOR_COLORING_PAGE_1234567890'
              }
            }
          ]
        }
      }
    ]
  };

  const image = extractImageBase64(mockSnakeCaseResponse);
  assert.equal(image, 'SNAKE_CASE_BASE64_DATA_MOCK_TEST_VALUE_FOR_COLORING_PAGE_1234567890');
});

test('extractImageBase64 returns null if no image in response', () => {
  const mockTextOnlyResponse = {
    candidates: [
      {
        content: {
          parts: [
            { text: 'Just text response' }
          ]
        }
      }
    ]
  };

  assert.equal(extractImageBase64(mockTextOnlyResponse), null);
  assert.equal(extractImageBase64(null), null);
  assert.equal(extractImageBase64({}), null);
});

// HTTP Integration Tests
const { app } = require('../server');

test('HTTP GET /health returns 200 OK and CORS headers', async (t) => {
  const server = app.listen(0);
  t.after(() => server.close());

  const port = server.address().port;
  const res = await fetch(`http://127.0.0.1:${port}/health`);

  assert.equal(res.status, 200);
  assert.equal(res.headers.get('access-control-allow-origin'), '*');

  const body = await res.json();
  assert.equal(body.status, 'ok');
  assert.equal(body.service, 'ColorAI Kids Backend');
  assert.equal(body.model, 'gemini-3.1-flash-image-preview');
});

test('HTTP POST /api/generate-coloring-page returns 400 on child safety violation', async (t) => {
  const server = app.listen(0);
  t.after(() => server.close());

  const port = server.address().port;
  const res = await fetch(`http://127.0.0.1:${port}/api/generate-coloring-page`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      character: 'spiderman',
      location: 'city',
      action: 'fighting'
    })
  });

  assert.equal(res.status, 400);
  const body = await res.json();
  assert.equal(typeof body.error, 'string');
  assert.match(body.error, /friendly, fun, and original drawing idea/i);
  assert.equal(body.isQuotaExceeded, false);
});

test('HTTP POST /api/generate-coloring-page returns 500 when GEMINI_API_KEY is missing', async (t) => {
  const originalKey = process.env.GEMINI_API_KEY;
  delete process.env.GEMINI_API_KEY;

  const server = app.listen(0);
  t.after(() => {
    server.close();
    if (originalKey) process.env.GEMINI_API_KEY = originalKey;
  });

  const port = server.address().port;
  const res = await fetch(`http://127.0.0.1:${port}/api/generate-coloring-page`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      character: 'Cute Teddy Bear',
      location: 'Sunny Garden',
      action: 'Picking Flowers'
    })
  });

  assert.equal(res.status, 500);
  const body = await res.json();
  assert.match(body.error, /Server API key is not configured/i);
  assert.equal(body.isQuotaExceeded, false);
});

