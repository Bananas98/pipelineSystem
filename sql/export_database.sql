LOAD DATA INFILE 'C:/Users/david/IdeaProjects/ExampleLibrary/TestApp/result.csv'
    INTO TABLE `result`
    FIELDS TERMINATED BY ';' ENCLOSED BY '"' ESCAPED BY '\\'
    LINES STARTING BY '' TERMINATED BY '\r\n'
    IGNORE 1 LINES;