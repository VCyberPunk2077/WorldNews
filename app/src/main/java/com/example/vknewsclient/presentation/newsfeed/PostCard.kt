package com.example.vknewsclient.presentation.newsfeed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.vknewsclient.R
import com.example.vknewsclient.domain.entity.FeedPost

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
) {
    Card(
        modifier = modifier
            .systemBarsPadding(),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        val uriHandler = LocalUriHandler.current
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(feedPost)
            Spacer(Modifier.height(8.dp))
            Text(
                text = feedPost.title ?: "",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    uriHandler.openUri(feedPost.url)
                },
            )
            Spacer(Modifier.height(8.dp))
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                        uriHandler.openUri(feedPost.url)
                    },
                model = feedPost.contentImageUrl,
                placeholder = painterResource(R.drawable.post_image_placeholder),
                contentDescription = stringResource(R.string.post_image),
            )
        }
    }
}

@Composable
private fun PostHeader(feedPost: FeedPost) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = feedPost.sourceName,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = feedPost.publicationDate,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Icon(
            modifier = Modifier
                .padding(8.dp),
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = stringResource(R.string.menu),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}