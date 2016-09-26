### @authour Shiva

# Setting up twitteR package
library(twitteR)
library(jsonlite)

# Setting up Authourization
setup_twitter_oauth("mNW6QMq6PNRkJ4iAWvBNDyOyE", "gmvDTGyoEqRYAgYk2gXyrHXGygXSj4IstMe8IemfMqnp9z4ggW" , "3600426982-OMGg9AxZ3HOA2mrERJHqZwEKkLzCzqIFzzgim1W", "AXIoGKnJauo4WyO5mf5bihmlmGdiY5pSqcgG7uTMm883v")

# Search for Tweets rentals/apartments , Remove duplicates on data returned
rental_tweets = strip_retweets(searchTwitter("rentals, NYC", n=1500 ,lang="en",locale = c(-125, 25, -66, 50), resultType="recent"))
write.csv(rental_tweets , "tweets.csv")
# convert to dataframe
df = do.call("rbind", lapply(rental_tweets, as.data.frame))

myjson = toJSON(df, pretty=TRUE)

# Savefiles to disk
write(myjson, "tweets.json")
