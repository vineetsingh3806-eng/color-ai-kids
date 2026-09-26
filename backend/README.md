# ColorAI Kids - Secure Backend Service

This service acts as the secure server proxy between the ColorAI Kids Android application and the Google Gemini / Imagen 3 API.

## Architecture

- **Client**: ColorAI Kids Android app (never holds secrets or API keys)
- **Backend**: Node.js 20 Express service deployed to Google Cloud Run (holds `GEMINI_API_KEY`)
- **Upstream AI**: Google AI Studio / Gemini 3.1 Flash Image (`gemini-3.1-flash-image-preview`) using `generateContent` API

## Contract Specification

### 1. Endpoint
`POST /api/generate-coloring-page`

### 2. Request Body
```json
{
  "character": "Friendly Dino",
  "location": "Jungle",
  "action": "Dancing",
  "prompt": "Children's coloring book page..."
}
```

### 3. Response Body
- **Success (HTTP 200)**:
```json
{
  "image": "<base64_encoded_png_image>"
}
```
- **Error (HTTP 400 / 429 / 500)**:
```json
{
  "error": "Error description",
  "isQuotaExceeded": false
}
```

## Quick Deployment to Google Cloud Run (Recommended)

### Prerequisites

1. Install the [Google Cloud SDK](https://cloud.google.com/sdk/docs/install) (`gcloud`).
2. Authenticate and set your active project:
   ```bash
   gcloud auth login
   gcloud config set project YOUR_GOOGLE_CLOUD_PROJECT_ID
   ```
3. Enable the required Google Cloud APIs:
   ```bash
   gcloud services enable \
     run.googleapis.com \
     cloudbuild.googleapis.com \
     artifactregistry.googleapis.com \
     secretmanager.googleapis.com
   ```

### Option 1: Deploy with Secret Manager (Recommended for Production Security)

Storing your `GEMINI_API_KEY` in Google Cloud Secret Manager ensures your key is never stored in plain text or visible in build logs:

1. Create the secret:
   ```bash
   echo -n "YOUR_ACTUAL_GEMINI_API_KEY" | gcloud secrets create gemini-api-key \
     --data-file=- \
     --replication-policy="automatic"
   ```

2. Deploy the backend from the `backend/` directory:
   ```bash
   cd backend
   gcloud run deploy colorai-backend \
     --source . \
     --region us-central1 \
     --allow-unauthenticated \
     --set-secrets GEMINI_API_KEY=gemini-api-key:latest \
     --set-env-vars GEMINI_MODEL="gemini-3.1-flash-image-preview"
   ```

### Option 2: Direct Deploy with Environment Variable (Quick Start)

```bash
cd backend
gcloud run deploy colorai-backend \
  --source . \
  --region us-central1 \
  --allow-unauthenticated \
  --set-env-vars GEMINI_API_KEY="YOUR_ACTUAL_GEMINI_API_KEY",GEMINI_MODEL="gemini-3.1-flash-image-preview"
```

### 3. Connect Android App

1. Copy the HTTPS Service URL output by Cloud Run (e.g. `https://colorai-backend-xxxxxx-uc.a.run.app`).
2. In your Android app `.env` file or the AI Studio Secrets panel, configure:
   ```env
   AI_BACKEND_URL=https://colorai-backend-xxxxxx-uc.a.run.app
   ```
3. Note: The Android app does NOT require any `GEMINI_API_KEY` when `AI_BACKEND_URL` is set.

---

## Environment Variables

| Variable | Description | Required | Default |
|---|---|---|---|
| `GEMINI_API_KEY` | Google Gemini API Key | **Yes** | None |
| `GEMINI_MODEL` | Gemini Image Model | No | `gemini-3.1-flash-image-preview` |
| `GEMINI_TIMEOUT_MS` | Upstream API timeout in milliseconds | No | `55000` (55s) |
| `PORT` | Listening port (injected by Cloud Run) | No | `8080` |

---

## Local Development & Testing

1. Run automated test suite:
   ```bash
   npm test
   ```
2. Start server locally:
   ```bash
   npm start
   ```
3. For Android Emulator testing, point your app to `http://10.0.2.2:8080`.
