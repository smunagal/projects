setwd("C:/Users/Shiva Reddy/Desktop/DIC/Project/DIC Project 1/dds_datasets/")
require(gdata)
library(plyr)
library("reshape2")

## Read Data
bronx        = read.xls("rollingsales_bronx.xls",        pattern="BOROUGH")
brooklyn     = read.xls("rollingsales_brooklyn.xls",     pattern="BOROUGH") 
manhattan    = read.xls("rollingsales_manhattan.xls",    pattern="BOROUGH") 
queens       = read.xls("rollingsales_queens.xls",       pattern="BOROUGH") 
statenisland = read.xls("rollingsales_statenisland.xls", pattern="BOROUGH") 

# Bronx Data
bronx$SALE.PRICE.N = as.numeric(gsub("[^[:digit:]]","",bronx$SALE.PRICE))
names(bronx) = tolower(names(bronx))
bronx$gross.sqft = as.numeric(gsub("[^[:digit:]]","",bronx$gross.square.feet)) 
bronx$sale.date = as.Date(bronx$sale.date) 
bronx.sale = bronx[bronx$sale.price.n!=0,]
bronx.homes = bronx.sale[which(grepl("FAMILY", bronx.sale$building.class.category)),] 
bronx.homes$outliers <- (log(bronx.homes$sale.price.n) <=5) + 0 
bronx.homes <- bronx.homes[which(bronx.homes$outliers==0),]

# Brooklyn Data
brooklyn$SALE.PRICE.N = as.numeric(gsub("[^[:digit:]]","",brooklyn$SALE.PRICE))
names(brooklyn) = tolower(names(brooklyn))
brooklyn$gross.sqft = as.numeric(gsub("[^[:digit:]]","",brooklyn$gross.square.feet)) 
brooklyn$sale.date = as.Date(brooklyn$sale.date) 
brooklyn.sale = brooklyn[brooklyn$sale.price.n!=0,]
brooklyn.homes = brooklyn.sale[which(grepl("FAMILY", brooklyn.sale$building.class.category)),] 
brooklyn.homes$outliers <- (log(brooklyn.homes$sale.price.n) <=5) + 0 
brooklyn.homes <- brooklyn.homes[which(brooklyn.homes$outliers==0),]

# Manhattan Data
manhattan$SALE.PRICE.N = as.numeric(gsub("[^[:digit:]]","",manhattan$SALE.PRICE))
names(manhattan) = tolower(names(manhattan))
manhattan$gross.sqft = as.numeric(gsub("[^[:digit:]]","",manhattan$gross.square.feet)) 
manhattan$sale.date = as.Date(manhattan$sale.date) 
manhattan.sale = manhattan[manhattan$sale.price.n!=0,]
manhattan.homes = manhattan.sale[which(grepl("FAMILY", manhattan.sale$building.class.category)),] 
manhattan.homes$outliers <- (log(manhattan.homes$sale.price.n) <=5) + 0 
manhattan.homes <- manhattan.homes[which(manhattan.homes$outliers==0),]

# Queens Data
queens$SALE.PRICE.N = as.numeric(gsub("[^[:digit:]]","",queens$SALE.PRICE))
names(queens) = tolower(names(queens))
queens$gross.sqft = as.numeric(gsub("[^[:digit:]]","",queens$gross.square.feet)) 
queens$sale.date = as.Date(queens$sale.date) 
queens.sale = queens[queens$sale.price.n!=0,]
queens.homes = queens.sale[which(grepl("FAMILY", queens.sale$building.class.category)),] 
queens.homes$outliers <- (log(queens.homes$sale.price.n) <=5) + 0 
queens.homes <- queens.homes[which(queens.homes$outliers==0),]

# Statenisland Data
statenisland$SALE.PRICE.N = as.numeric(gsub("[^[:digit:]]","",statenisland$SALE.PRICE))
names(statenisland) = tolower(names(statenisland))
statenisland$gross.sqft = as.numeric(gsub("[^[:digit:]]","",statenisland$gross.square.feet)) 
statenisland$sale.date = as.Date(statenisland$sale.date) 
statenisland.sale = statenisland[statenisland$sale.price.n!=0,]
statenisland.homes = statenisland.sale[which(grepl("FAMILY", statenisland.sale$building.class.category)),] 
statenisland.homes$outliers <- (log(statenisland.homes$sale.price.n) <=5) + 0 
statenisland.homes <- statenisland.homes[which(statenisland.homes$outliers==0),]

 
###
num = function(x){length(x)}
SperDay1 =  summaryBy(bronx$sale.price.n~bronx$sale.date, data = bronx, FUN=num)
SperDay2 =  summaryBy(brooklyn$sale.price.n~brooklyn$sale.date, data = brooklyn, FUN=num)
SperDay3 =  summaryBy(manhattan$sale.price.n~manhattan$sale.date, data = manhattan, FUN=num)
SperDay4 =  summaryBy(queens$sale.price.n~queens$sale.date, data = queens, FUN=num)
SperDay5 =  summaryBy(statenisland$sale.price.n~statenisland$sale.date, data = statenisland, FUN=num)

bronx_city = data.frame(SperDay1)
brooklyn_city  = data.frame(SperDay2)
manhattan_city = data.frame(SperDay3)
queens_city = data.frame(SperDay4)
statenisland_city = data.frame(SperDay5)


bronx_city = rename(bronx_city, c("sale.date"="Date", "bronx.sale.price.n.num"="No. of Sales"))
brooklyn_city = rename(brooklyn_city, c("sale.date"="Date", "brooklyn.sale.price.n.num"="No. of Sales"))
manhattan_city = rename(manhattan_city, c("sale.date"="Date", "manhattan.sale.price.n.num"="No. of Sales"))
queens_city = rename(queens_city, c("sale.date"="Date", "queens.sale.price.n.num"="No. of Sales"))
statenisland_city = rename(statenisland_city, c("sale.date"="Date", "statenisland.sale.price.n.num"="No. of Sales"))

bronx_city["city"] = "bronx"
brooklyn_city["city"] = "brooklyn"
manhattan_city["city"] = "manhattan"
queens_city["city"] = "queens"
statenisland_city["city"] = "statenisland"

plotdata = rbind(bronx_city , brooklyn_city , manhattan_city , queens_city , statenisland_city)

### Number of sales per day compared to all Cities

(ggplot(data = plotdata, aes(x = plotdata$Date, y = (plotdata$`No. of Sales`), color = plotdata$city)) 
+ geom_line()
+ ggtitle("Number Of sales from Aug 2012 to Aug 2013")
+ xlab("Months") 
+ ylab("Number of sales per Date/Day")
)














