#Setting up twitteR package
#install.packages("twitteR", dependencies = T)
library(twitteR)
library(jsonlite)
library(doBy)
library(plyr)
library(ggplot2)

# Setting up Authourization

setup_twitter_oauth("mNW6QMq6PNRkJ4iAWvBNDyOyE", "gmvDTGyoEqRYAgYk2gXyrHXGygXSj4IstMe8IemfMqnp9z4ggW" , "3600426982-OMGg9AxZ3HOA2mrERJHqZwEKkLzCzqIFzzgim1W", "AXIoGKnJauo4WyO5mf5bihmlmGdiY5pSqcgG7uTMm883v")

# Rent Tweets and Sale Tweets
house_rents = strip_retweets((searchTwitter("rent + house", n=2500 ,locale = c(-125, 25, -66, 50),lang="en", resultType="recent")))
apartment_rents = strip_retweets(searchTwitter("rent + apartment", locale = c(-125, 25, -66, 50),n=2500 ,lang="en", resultType="recent"))
house_sales = strip_retweets(searchTwitter("sale + house", n=2500 ,locale = c(-125, 25, -66, 50),lang="en", resultType="recent"))
apartment_sales = strip_retweets(searchTwitter("sale + apartment + ", locale = c(-125, 25, -66, 50),n=2500 ,lang="en", resultType="recent"))

# Create Data Frame
house_rents = do.call("rbind", lapply(house_rents, as.data.frame))
house_sales = do.call("rbind", lapply(house_sales, as.data.frame))
apartment_rents = do.call("rbind", lapply(apartment_rents, as.data.frame))
apartment_sales = do.call("rbind", lapply(apartment_sales, as.data.frame))

# Formating Date field
house_rents$created =  as.character(substr(house_rents$created,1,10))
house_sales$created =  as.character(substr(house_sales$created,1,10))
apartment_rents$created =  as.character(substr(apartment_rents$created,1,10))
apartment_sales$created =  as.character(substr(apartment_sales$created,1,10))

# collapse twitter data by summarizing on tweets per day
House_rent_tweets = ddply(house_rents, c("created"), summarise, N = length(id) )
House_sale_tweetS = ddply(house_sales, c("created"), summarise, N = length(id) )
Apartment_rent_tweets = ddply(apartment_rents, c("created"), summarise, N = length(id) )
Apartment_sale_tweets = ddply(apartment_sales, c("created"), summarise, N = length(id) )

# Sample plot for tweets per day

houserent = (ggplot(House_rent_tweets, aes(x = House_rent_tweets$created, y =House_rent_tweets$N))
              + geom_bar(stat="identity",  width = 0.50)
              + ggtitle("House Rental listings")
              + xlab("Date") 
              + ylab("House Rental listings per day")
)

housesales = (ggplot(House_sale_tweetS, aes(x = House_sale_tweetS$created, y =House_sale_tweetS$N))
                + geom_bar(stat="identity",  width = 0.50)
                + ggtitle("House sales listings")
                + xlab("Date") 
                + ylab("House sales listings per day")
)

Aptrent = (ggplot(Apartment_rent_tweets, aes(x = Apartment_rent_tweets$created, y =Apartment_rent_tweets$N))
              + geom_bar(stat="identity",  width = 0.50)
              + ggtitle("Aptsales Rental listings")
              + xlab("Date") 
              + ylab("Apartment Rental listings per day")
)


Aptsales = (ggplot(Apartment_sale_tweets, aes(x = Apartment_sale_tweets$created, y =Apartment_sale_tweets$N))
                + geom_bar(stat="identity",  width = 0.50)
                + ggtitle("Aptsales sales listings")
                + xlab("Date") 
                + ylab("Apartment Sales listings per day")
)


p = list(houserent , housesales)
str(p2)
g = list(p5,p6 , p7 , p8)
do.call(grid.arrange, c(p ,  list(ncol=2)))


g = list(Aptrent , Aptsales)
do.call(grid.arrange, c(g ,  list(ncol=2)))

do.call(grid.arrange, c( list(Aptsales, housesales), list(Aptrent, houserent) ,  list(ncol=2)))
 