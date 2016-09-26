### @authour Shiva

# Import Library
library(jsonlite)
library(plyr)
library(ggplot2)

# read file from memory
json_data = read.csv("tweets.csv")
df = as.data.frame(json_data)

str(df)
# Format date field

df$created =  as.character(substr(df$created,1,10))

# collapse twitter data by summarizing on tweets per day
cdata <- ddply(df, c("created"), summarise, N = length(id) )

# Sample plot for tweets per day
(ggplot(cdata, aes(x = cdata$created, y =cdata$N)) + geom_bar(stat="identity")+
  ggtitle("number of tweets per day")
+ xlab("Date") 
+ ylab("Tweets per day"))