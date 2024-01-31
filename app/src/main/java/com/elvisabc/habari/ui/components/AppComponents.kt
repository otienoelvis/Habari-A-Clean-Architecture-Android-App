package com.elvisabc.habari.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elvisabc.habari.data.entity.NewsResponse
import com.elvisabc.habari.ui.theme.Purple40
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.elvisabc.habari.R
import com.elvisabc.habari.data.entity.Article
import com.elvisabc.utilities.CoreUtility


@Composable
fun Loader(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Purple40
        )
    }

}

@Composable
fun NewsList(response: NewsResponse) {

    LazyColumn{
        response.articles?.let { articles ->
            items(articles){ article ->
                NormalTextComponent(value = article.title ?: "NA")
            }
        }

    }
}

@Composable
fun NormalTextComponent(value: String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp),
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    )
}

@Composable
fun HeadingTextComponent(textValue: String, centerAligned: Boolean = false){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        ),
        textAlign = if(centerAligned) TextAlign.Center else TextAlign.Start
    )
}

@Composable
fun NewsRowComponent(page: Int, article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White)
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(
                    shape = RoundedCornerShape(6.dp)
                ),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.image_placeholder),
            error = painterResource(id = R.drawable.image_placeholder),
        )

        Spacer(modifier = Modifier.size(8.dp))

        HeadingTextComponent(article.title ?: "")

        Spacer(modifier = Modifier.size(4.dp))

        NormalTextComponent(article.description ?: "")

        Spacer(modifier = Modifier.size(4.dp))

        AuthorDetailsComposable(article.author, article.source?.name)
    }
}

@Composable
fun AuthorDetailsComposable(authorName: String?, sourceName: String?){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ){
        Text(
            text = authorName ?: ""
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = sourceName ?: ""
        )
    }
}

@Composable
fun EmptyStateComponent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.nothing),
            contentDescription = null
        )

        HeadingTextComponent(
            textValue = stringResource(R.string.couldn_t_get_any_news_now_please_check_again_later),
            centerAligned = true
        )

    }
}


@Composable
fun internetComponent(): Boolean{
    return CoreUtility.isInternetConnected(LocalContext.current)
}
//@Preview(showBackground = false)
//@Composable
//fun NewsRowComponentPreview(page: Int, article: Article) {
//    val item = Article(
//    author = "Zakayo",
//    title = "Breathing Taxes",
//    description = "Tolls to be placed outside your house!",
//    url = "https://www.citizen.digital/business/govt-to-introduce-charges-for-using-major-roads-in-nairobi-transport-cs-murkomen-n335608",
//    urlToImage = "https://citizentv.s3.amazonaws.com/113122/conversions/Ruto-og_image.webp",
//    publishedAt = "",
//    content = "You will soon have to pay to use major roads in the country, Transport Cabinet Secretary (CS) Kipchumba Murkomen has said.\n" +
//            "\n" +
//            "Murkomen made the announcement on Friday during the launch of the Kenya National Highways Authority strategic plan for 2023 to 2027, adding that the government plans to not only expand major roads but to also charge road users for using the roads.\n" +
//            "\n" +
//            "While hailing the public private funding model that was implemented for the Nairobi Expressway, the CS highlighted that the State will begin implementing the road toll model on various commonly used roads",
//    source = Source(
//        id = "",
//        name = null
//    )
//    )
//    NewsRowComponent(0, item)
//}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(){
//    TopAppBar(title = { /*TODO*/ })
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .background(color = Color.White)
    ) {
        SearchTextComponent(onSearchTextEntered={} )
    }
}

@Composable
fun SearchTextComponent(textValue: String = "", onSearchTextEntered: (String) -> Unit){

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            onSearchTextEntered.invoke(it)
        },
        textStyle = TextStyle(),
        placeholder = {
            Text(
                text = "Search",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp
                ),
            )
        },
        shape = RoundedCornerShape(24.dp),
        trailingIcon = {
            Image(imageVector = Icons.Outlined.Search, contentDescription = "search news icon")
        }
    )
}

@Preview(showBackground = false)
@Composable
fun TopBarComponentPreview(){
    TopBarComponent()
}

@Preview(showBackground = true)
@Composable
fun SearchTextComponentPreview(){
    SearchTextComponent(textValue = "Search", onSearchTextEntered = {})
}



        