
# @author : Shiva
# Adding Libraries Required : 
library(doBy)
library(ggplot2)
library(gridExtra)
# Reading csv file
data1 = read.csv("nyt1.csv",header = T)

# Adding agecat categerization variable based on observation data
data1$agecat =cut(data1$Age,c(-Inf,0,18,24,34,44,54,64,Inf))

# create click thru rate  coloumn for non-zero observations
data1_imps = subset(data1, Impressions>0 & Clicks>0)
data1_imps$CTR = data1_imps$Impressions/data1_imps$Clicks
names(data1_imps)
clen <- function(x){c(length(x))}
data1$scode[data1$Impressions==0] = "NoImps" 
data1$scode[data1$Impressions >0] = "Imps" 
data1$scode[data1$Clicks >0] = "Clicks"
etable<-summaryBy(Impressions~scode+Gender+agecat, data = data1, FUN=clen) 
etable$Gender = as.factor(etable$Gender)
# Clicks  variation for gender
(ggplot(data = subset(etable, etable$scode == "Clicks"), aes(x =agecat, y =  Impressions.clen, color =  Gender) ) 
      + geom_point(size = 5)
      + xlab("Age categories") 
      + ylab("Impressions with Clicks >0") 
      + guides(fill=guide_legend(title="Impressions vs Age categeries"))
      + ggtitle("Number of Impression per Age categeries and Gender ") 
)
