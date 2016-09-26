# @author : Shiva
# Adding Libraries Required : 
library(doBy)
library(ggplot2)
library(gridExtra)
library(plyr)
setwd("C:/Users/Shiva Reddy/Desktop/DIC/Project/DIC Project 1/dds_datasets/nyt")
# Function

CTR_summary = function(input){
  input$agecat =cut(input$Age,c(-Inf,0,18,24,34,44,54,64,Inf))
  # create click thru rate  coloumn for non-zero observations
  data_imps = subset(input, Impressions>0 & Clicks>0)
  data_imps$CTR = data_imps$Impressions/data_imps$Clicks
  # Summarize CTR and Impressions
  cmean = function(x){c(mean(x))}
  plot_data = summaryBy(CTR+Impressions~agecat, data = data_imps, FUN=cmean)
  return(data.frame(plot_data))
}

## Summarize 31 days data into a single dataframe one dataset at a time
ldf = list()
listcsv = dir(pattern = "*.csv")
for (k in 1:length(listcsv)){
  ldf[[k]] = CTR_summary(read.csv(listcsv[k]))
}



days = list()
age1 = list()

for(i in 1:length(ldf)){
  age1[i] = ldf[[i]]$CTR.cmean[ldf[[i]]$agecat == "(-Inf,0]"]
  
}

## Group data from summary list

ag1 = data.frame(x = numeric(), y =numeric())
ag2 = data.frame(x = numeric(), y =numeric())
ag3 = data.frame(x = numeric(), y =numeric())
ag4 = data.frame(x = numeric(), y =numeric())
ag5 = data.frame(x = numeric(), y =numeric())
ag6 = data.frame(x = numeric(), y =numeric())
ag7 = data.frame(x = numeric(), y =numeric())
ag8 = data.frame(x = numeric(), y =numeric())
 ldf
for(i in 1:length(ldf)){
  temp = data.frame(ldf[[i]])
  ag1 = rbind(ag1,c(i, temp[1,2]))
  ag2 = rbind(ag2,c(i, temp[2,2]))
  ag3 = rbind(ag3,c(i, temp[3,2]))
  ag4 = rbind(ag4,c(i, temp[4,2]))
  ag5 = rbind(ag5,c(i, temp[5,2]))
  ag6 = rbind(ag6,c(i, temp[6,2]))
  ag7 = rbind(ag7,c(i, temp[7,2]))
  ag8 = rbind(ag8,c(i, temp[8,2]))
}
str(ag1)
str(ag2)
str(ag3)
str(ag4)
str(ag5)
str(ag6)
str(ag7)
str(ag8)

ag1 = rename(ag1, c("X1"="Days", "X5.61050948844884"="CTR"))
ag2 = rename(ag2, c("X1"="Days", "X5.69534654857304"="CTR"))
ag3 = rename(ag3, c("X1"="Days", "X5.95476333133613"="CTR"))
ag4 = rename(ag4, c("X1"="Days", "X5.84982578397213"="CTR"))
ag5 = rename(ag5, c("X1"="Days", "X5.94014476614699"="CTR"))
ag6 = rename(ag6, c("X1"="Days", "X5.89821599235425"="CTR"))
ag7 = rename(ag7, c("X1"="Days", "X5.80766274690646"="CTR"))
ag8 = rename(ag8, c("X1"="Days", "X5.77509794319295"="CTR"))

p1 = (ggplot(ag1, aes(x=ag1$Days, y=ag1$CTR)) + geom_line()  + ggtitle(" Age category (-Inf,0] ") + xlab("Days") + ylab("CTR Mean") )
p2 = (ggplot(ag2, aes(x=ag2$Days, y=ag2$CTR)) + geom_line()  + ggtitle(" Age category (0,18] ") + xlab("Days") + ylab("CTR Mean") ) 
p3 = (ggplot(ag3, aes(x=ag3$Days, y=ag3$CTR)) + geom_line()  + ggtitle(" Age category (18,24] ") + xlab("Days") + ylab("CTR Mean") )
p4 = (ggplot(ag4, aes(x=ag4$Days, y=ag4$CTR)) + geom_line()  + ggtitle(" Age category (24,34] ") + xlab("Days") + ylab("CTR Mean") ) 
p5 = (ggplot(ag5, aes(x=ag5$Days, y=ag5$CTR)) + geom_line()  + ggtitle(" Age category (34,44] ") + xlab("Days") + ylab("CTR Mean") )
p6 = (ggplot(ag6, aes(x=ag6$Days, y=ag6$CTR)) + geom_line()  + ggtitle(" Age category (44,54] ") + xlab("Days") + ylab("CTR Mean") )
p7 = (ggplot(ag7, aes(x=ag7$Days, y=ag7$CTR)) + geom_line()  + ggtitle(" Age category (54,64] ") + xlab("Days") + ylab("CTR Mean") )
p8 = (ggplot(ag8, aes(x=ag8$Days, y=ag8$CTR)) + geom_line()  + ggtitle(" Age category (-64, Inf] ") + xlab("Days") + ylab("CTR Mean") )

 
p = list(p1 , p2 , p3 , p4)
str(p2)
g = list(p5,p6 , p7 , p8)
do.call(grid.arrange, c(p, g, list(ncol=4)))
