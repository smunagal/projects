library(shiny)
library(streamR)
library(RCurl)
library(ROAuth)
library(ggplot2)
library(grid)
library(twitteR)
library(jsonlite)
library(doBy)
library(plyr)
library(ggplot2)
library(gridExtra)


ui = fluidPage(
  sliderInput(inputId = "num", label = "Collect data from twitter Stream API for given seconds" , 
              value = 20, min =1, max =100)
  ,  actionButton(inputId = "search", label = "Search")
  ,  plotOutput("day")
  ,  plotOutput("week")
  
) 

server = function(input, output){
    data = eventReactive(input$search, {
          load("my_oauth.Rdata")
          filterStream("EData.json", track = c("#ELection2016"),locations = c(-125, 25, -66, 50), timeout = input$num, oauth = my_oauth)
          EData.df = parseTweets("EData.json", simplify = TRUE)
          #write.csv(EData.df,"EData.df")
          
          val = c( length(grep("Trump", EData.df$text, ignore.case = TRUE)),
                   length(grep("Clinton", EData.df$text, ignore.case = TRUE)),
                   length(grep("Ted Cruz", EData.df$text, ignore.case = TRUE)))      
          
        })
    
    data1 = eventReactive(input$search, {
      
      weekly_tweets = read.csv("ElectionData.csv", header=T)
      
      
      # Tweets per day summary
      
      weekly.Trump   = weekly_tweets[which(grepl("trump", weekly_tweets$text)),] 
      weekly.Clinton = weekly_tweets[which(grepl("Clinton", weekly_tweets$text)),] 
      weekly.Ted     = weekly_tweets[which(grepl("Ted Cruz", weekly_tweets$text)),] 
      
      weekly.Trump$created =  as.character(substr(weekly.Trump$created,1,10))
      weekly.Clinton$created =  as.character(substr(weekly.Clinton$created,1,10))
      weekly.Ted$created =  as.character(substr(weekly.Ted$created,1,10))
      
      weekly.Trump.tweets = ddply(weekly.Trump, c("created"), summarise, N = length(id) )
      weekly.Clinton.tweets = ddply(weekly.Clinton, c("created"), summarise, N = length(id) )
      weekly.Ted.tweets = ddply(weekly.Ted, c("created"), summarise, N = length(id) )
      
      # plots
      
      Trump = (ggplot(weekly.Trump.tweets, aes(x = weekly.Trump.tweets$created, y =weekly.Trump.tweets$N))
               + geom_bar(stat="identity",  width = 0.50)
               + ggtitle("Donald Trump Weekly Count of Tweets")
               + xlab("Date") 
               + ylab("Tweets per day")
      )
      Clinton = (ggplot(weekly.Clinton.tweets, aes(x = weekly.Clinton.tweets$created, y =weekly.Clinton.tweets$N))
                 + geom_bar(stat="identity",  width = 0.50)
                 + ggtitle("Clinton weekly count of tweets")
                 + xlab("Date") 
                 + ylab("Tweets per day")
      )
      
      Ted = (ggplot(weekly.Ted.tweets, aes(x = weekly.Ted.tweets$created, y =weekly.Ted.tweets$N))
             + geom_bar(stat="identity",  width = 0.50)
             + ggtitle("Ted's weekly count of tweets")
             + xlab("Date") 
             + ylab("Tweets per day")
      )
      p = list(Trump,Clinton,Ted)   
      
    })
    ## Server code
    output$day = renderPlot({
        barplot(data(), names.arg = c("Donald Trump", "Clinton" ,"Ted Cruz"), ylab = "Number of supporting tweets"   )})
    
    output$week = renderPlot({
      do.call(grid.arrange, c(data1() ,  list(ncol=1)))})
    
}



shinyApp(ui = ui, server = server)
 
