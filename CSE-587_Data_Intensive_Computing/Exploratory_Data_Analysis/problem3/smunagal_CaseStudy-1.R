#install.packages("gdata")
setwd("C:/Users/Shiva Reddy/Desktop/DIC/Project/DIC Project 1/dds_datasets/")
require(gdata)

# ### My code:
brooklyn = read.xls("rollingsales_brooklyn.xls",pattern="BOROUGH") 
#format sales price :
brooklyn$SALE.PRICE.N = as.numeric(gsub("[^[:digit:]]","",brooklyn$SALE.PRICE))
summary(brooklyn)
names(brooklyn) = tolower(names(brooklyn))
brooklyn$gross.sqft = as.numeric(gsub("[^[:digit:]]","",brooklyn$gross.square.feet)) 
brooklyn$land.sqft = as.numeric(gsub("[^[:digit:]]","",brooklyn$land.square.feet))
brooklyn$sale.date = as.Date(brooklyn$sale.date) 
brooklyn$year.built = as.numeric(as.character(brooklyn$year.built))
# Actual sales
brooklyn.sale = brooklyn[brooklyn$sale.price.n!=0,]
plot(log(brooklyn.sale$gross.sqft),log(brooklyn.sale$sale.price.n))
brooklyn.homes = brooklyn.sale[which(grepl("FAMILY", brooklyn.sale$building.class.category)),] 
## remove outliers that seem like they weren't actual sales 
brooklyn.homes$outliers = (log(brooklyn.homes$sale.price.n) <=5) + 0 
brooklyn.homes = brooklyn.homes[which(brooklyn.homes$outliers==0),]
plot(log(brooklyn.homes$gross.sqft),log(brooklyn.homes$sale.price.n))


brooklyn.apartments = brooklyn.sale[which(grepl("APARTMENTS", brooklyn.sale$building.class.category)),] 
brooklyn.apartments$outliers = (log(brooklyn.apartments$sale.price.n) <=5) + 0 
brooklyn.apartments = brooklyn.apartments[which(brooklyn.apartments$outliers==0),]
###
Mean_price = function(x){mean(x)}
brooklyn.AvgPrice = summaryBy(brooklyn$sale.price.n ~ brooklyn$neighborhood, data = brooklyn, FUN = Mean_price)
str(brooklyn.AvgPrice)

## Normalizing the curve
neigbhour_plot = ggplot(data = brooklyn.AvgPrice , aes(x = brooklyn.AvgPrice$neighborhood, y = brooklyn.AvgPrice$`brooklyn$sale.price.n.Mean_price`))
(neigbhour_plot +  geom_bar(stat="identity", width = 0.7 )
    + theme(axis.text.x=element_text(angle=90,hjust=1,vjust=0.5))
    + ggtitle("Neighborhood Avg. sale Prices")
    + xlab("NEIGHBORHOOD") 
    + ylab("Average Price") )

boxplot((brooklyn.homes$sale.price.n)~brooklyn.homes$neighborhood,data=brooklyn, main="Box Plot", 
      xlab="NEIGHBORHOOD", ylab="Average Price" , las = 2)

## Prediction curve
## House
sft_Plot_house = ggplot(brooklyn.homes, aes(x = log(brooklyn.homes$gross.sqft), y = log(brooklyn.homes$sale.price.n)))
(sft_Plot_house + geom_point(aes(color = (brooklyn.homes$neighborhood) ) )   
    + ggtitle("House Area in sft vs sale Prices(Normalized) ")
    + xlab("House Area in sft") 
    + ylab("sale Prices") 
    + geom_smooth())
## Apartment
sft_Plot_Apartments = ggplot(brooklyn.apartments, aes(x = log(brooklyn.apartments$gross.sqft), y = log(brooklyn.apartments$sale.price.n)))
(sft_Plot_Apartments + geom_point(aes(color = (brooklyn.apartments$neighborhood) ) )   
+ ggtitle("Apartment Area in sft vs sale Prices(Normalized) ")
+ xlab("Apartment Area in sft") 
+ ylab("sale Prices") 
+ geom_smooth())


