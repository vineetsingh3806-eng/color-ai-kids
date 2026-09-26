package com.example.ui.artwork

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.SavedArtwork
import com.example.ui.components.KidButton
import com.example.ui.theme.CoralRed
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SurfaceWarm
import com.example.ui.theme.TextPrimaryDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.ui.theme.TextSecondaryDark
import com.example.ui.theme.WarmOrange
import com.example.utils.ImageStorageManager
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MyArtworkScreen(
    artworks: List<SavedArtwork>,
    onDeleteArtwork: (SavedArtwork) -> Unit,
    onColorNewClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    var selectedArtworkForView by remember { mutableStateOf<SavedArtwork?>(null) }
    var artworkToDelete by remember { mutableStateOf<SavedArtwork?>(null) }

    Scaffold(
        topBar = {
            Surface(
                color = Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF0F0F0))
                            .testTag("artwork_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextPrimaryDark
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "🖼️ My Masterpieces",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp
                        ),
                        color = TextPrimaryDark
                    )
                }
            }
        },
        containerColor = SurfaceWarm
    ) { innerPadding ->
        if (artworks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "🎨", fontSize = 68.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No Drawings Yet!",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        color = TextPrimaryDark
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Pick a picture or create with AI to color your very first masterpiece!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondaryDark,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    KidButton(
                        text = "Start Coloring 🚀",
                        onClick = onColorNewClick,
                        backgroundColor = WarmOrange,
                        modifier = Modifier.testTag("artwork_empty_start_button")
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(
                    items = artworks,
                    key = { it.id },
                    contentType = { "artwork_card" }
                ) { artwork ->
                    val onCardClick = remember(artwork.id) { { selectedArtworkForView = artwork } }
                    val onCardDelete = remember(artwork.id) { { artworkToDelete = artwork } }
                    val onCardShare = remember(artwork.id) {
                        {
                            val shareIntent = ImageStorageManager.createShareIntent(
                                context = context,
                                filePath = artwork.imageFilePath,
                                title = artwork.title
                            )
                            if (shareIntent != null) {
                                context.startActivity(shareIntent)
                            }
                        }
                    }
                    ArtworkCard(
                        artwork = artwork,
                        onClick = onCardClick,
                        onDelete = onCardDelete,
                        onShare = onCardShare
                    )
                }
            }
        }

        // Full Screen Viewer Dialog
        selectedArtworkForView?.let { artwork ->
            Dialog(onDismissRequest = { selectedArtworkForView = null }) {
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = artwork.title,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimaryDark
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        val file = File(artwork.imageFilePath)
                        if (file.exists()) {
                            val bmp = BitmapFactory.decodeFile(file.absolutePath)
                            if (bmp != null) {
                                Image(
                                    bitmap = bmp.asImageBitmap(),
                                    contentDescription = artwork.title,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(20.dp))
                                        .border(2.dp, Color(0x1F000000), RoundedCornerShape(20.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    val shareIntent = ImageStorageManager.createShareIntent(
                                        context = context,
                                        filePath = artwork.imageFilePath,
                                        title = artwork.title
                                    )
                                    if (shareIntent != null) {
                                        context.startActivity(shareIntent)
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Share")
                            }

                            Button(
                                onClick = { selectedArtworkForView = null },
                                colors = ButtonDefaults.buttonColors(containerColor = WarmOrange),
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text("Close")
                            }
                        }
                    }
                }
            }
        }

        // Delete Confirmation Dialog
        artworkToDelete?.let { artwork ->
            AlertDialog(
                onDismissRequest = { artworkToDelete = null },
                title = { Text(text = "Delete Drawing? 🗑️", fontWeight = FontWeight.Bold) },
                text = { Text("Are you sure you want to remove \"${artwork.title}\"?") },
                confirmButton = {
                    Button(
                        onClick = {
                            onDeleteArtwork(artwork)
                            artworkToDelete = null
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = CoralRed),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { artworkToDelete = null },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Keep")
                    }
                },
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

@Composable
private fun ArtworkCard(
    artwork: SavedArtwork,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit
) {
    val dateString = remember(artwork.createdAt) {
        val sdf = SimpleDateFormat("MMM d", Locale.getDefault())
        sdf.format(Date(artwork.createdAt))
    }

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("artwork_card_${artwork.id}")
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            val file = remember(artwork.imageFilePath) { File(artwork.imageFilePath) }
            val artworkBitmap by produceState<ImageBitmap?>(initialValue = null, artwork.imageFilePath) {
                if (file.exists()) {
                    value = withContext(Dispatchers.IO) {
                        try {
                            val options = BitmapFactory.Options().apply {
                                inPreferredConfig = Bitmap.Config.RGB_565
                            }
                            val bmp = BitmapFactory.decodeFile(file.absolutePath, options)
                            bmp?.asImageBitmap()
                        } catch (t: Throwable) {
                            null
                        }
                    }
                }
            }

            val bmp = artworkBitmap
            if (bmp != null) {
                Image(
                    bitmap = bmp,
                    contentDescription = artwork.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = artwork.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                ),
                color = TextPrimaryDark,
                maxLines = 1
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dateString,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondaryDark
                )

                Row {
                    IconButton(
                        onClick = onShare,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = SkyBlue,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.LightGray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}
