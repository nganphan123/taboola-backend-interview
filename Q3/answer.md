CREATE TABLE Product (
    Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(255),
  	Category VARCHAR(255),
  	AddTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  	Adder VARCHAR(255),
  	PRIMARY KEY (Id)
);

CREATE TABLE Price (
    Price DOUBLE,
    ProductID INT,
    DiscPercent DOUBLE,
    UpdateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Updater VARCHAR(255),
    FOREIGN KEY (ProductId) REFERENCES Product(Id)
);
CREATE TABLE PriceLog (
    Action VARCHAR(255),
    Time TIMESTAMP,
    Updater VARCHAR(255),
    ProductId INT,
    OldVal DOUBLE,
    NewVal DOUBLE,
    FOREIGN KEY (ProductId) REFERENCES Product(Id)
)

CREATE TRIGGER insert_priceLog AFTER INSERT ON Price
BEGIN
  INSERT INTO PriceLog
  VALUES ('insert', NOW(), NEW.Updater, NEW.ProductId, NULL, NEW.Price);
END$$

CREATE TRIGGER update_priceLog AFTER INSERT ON Price
BEGIN
  INSERT INTO PriceLog
  VALUES ('update', NOW(), NEW.Updater, NEW.ProductId, NULL, NEW.Price);
END$$