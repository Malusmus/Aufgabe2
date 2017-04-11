-- -----------------------------------------------------
-- Table vsisp41.EstateAgent
-- -----------------------------------------------------
CREATE TABLE vsisp41.EstateAgent (
  EstateAgentId INT NOT NULL ,
  Name VARCHAR(100) ,
  Address VARCHAR(150) ,
  Login VARCHAR(20) ,
  Password VARCHAR(20) ,
  PRIMARY KEY (EstateAgentId));


-- -----------------------------------------------------
-- Table vsisp41.Estate
-- -----------------------------------------------------
CREATE TABLE vsisp41.Estate (
  Id INT NOT NULL UNIQUE ,
  EstateAgentId INT NOT NULL ,
  City VARCHAR(50) ,
  PostalCode VARCHAR(10) ,
  Street VARCHAR(45) ,
  StreetNumber VARCHAR(5) ,
  SquareArea DECIMAL(10,2) ,
  PRIMARY KEY (Id, EstateAgentId),
  CONSTRAINT FK_ESTATE_AGENT
    FOREIGN KEY (EstateAgentId)
    REFERENCES vsisp41.EstateAgent (EstateAgentId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table vsisp41.Apartment
-- -----------------------------------------------------
CREATE TABLE vsisp41.Apartment (
  EstateId INT NOT NULL ,
  Floor INT ,
  Rent INT ,
  Rooms INT ,
  Balcony SMALLINT ,
  BuiltInKitchen SMALLINT ,
  PRIMARY KEY (EstateId),
  CONSTRAINT FK_ESTATE_APARTMENT
    FOREIGN KEY (EstateId)
    REFERENCES vsisp41.Estate (Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table vsisp41.House
-- -----------------------------------------------------
CREATE TABLE vsisp41.House (
  EstateId INT NOT NULL ,
  Floors INT ,
  Price INT ,
  Garden SMALLINT ,
  PRIMARY KEY (EstateId),
  CONSTRAINT FK_ESTATE_HOUSE
    FOREIGN KEY (EstateId)
    REFERENCES vsisp41.Estate (Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table vsisp41.Person
-- -----------------------------------------------------
CREATE TABLE vsisp41.Person (
  Id INT NOT NULL ,
  FirstName VARCHAR(45) ,
  Name VARCHAR(45) ,
  Address VARCHAR(150) ,
  PRIMARY KEY (Id));


-- -----------------------------------------------------
-- Table vsisp41.Contract
-- -----------------------------------------------------
CREATE TABLE vsisp41.Contract (
  ContractNo INT NOT NULL ,
  Date DATE ,
  Place VARCHAR(150) ,
  PRIMARY KEY (ContractNo));


-- -----------------------------------------------------
-- Table vsisp41.PurchaseContract
-- -----------------------------------------------------
CREATE TABLE vsisp41.PurchaseContract (
  ContractNo INT NOT NULL ,
  NoOfInstallments INT ,
  InterestRate DECIMAL(10,2) ,
  PRIMARY KEY (ContractNo),
  CONSTRAINT FK_PURCHASECONTRACT_CONTRACT
    FOREIGN KEY (ContractNo)
    REFERENCES vsisp41.Contract (ContractNo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table vsisp41.Sells
-- -----------------------------------------------------
CREATE TABLE vsisp41.Sells (
  HouseId INT NOT NULL ,
  PersonId INT NOT NULL ,
  PurchaseContractNo INT NOT NULL ,
  PRIMARY KEY (HouseId, PersonId, PurchaseContractNo),
  CONSTRAINT FK_SELLS_HOUSE
    FOREIGN KEY (HouseId)
    REFERENCES vsisp41.House (EstateId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_SELLS_PERSON
    FOREIGN KEY (PersonId)
    REFERENCES vsisp41.Person (Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_SELLS_PURCHASECONTRACT
    FOREIGN KEY (PurchaseContractNo)
    REFERENCES vsisp41.PurchaseContract (ContractNo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table vsisp41.TenancyContract
-- -----------------------------------------------------
CREATE TABLE vsisp41.TenancyContract (
  ContractNo INT NOT NULL ,
  StartDate DATE ,
  Duration DATE ,
  AdditionalCosts DECIMAL(10,2) ,
  PRIMARY KEY (ContractNo),
  CONSTRAINT FK_TENANCYCONTRACT_CONTRACT
    FOREIGN KEY (ContractNo)
    REFERENCES vsisp41.Contract (ContractNo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table vsisp41.Rents
-- -----------------------------------------------------
CREATE TABLE vsisp41.Rents (
  ApartmentId INT NOT NULL ,
  PersonId INT NOT NULL ,
  TenancyContractNo INT NOT NULL ,
  PRIMARY KEY (TenancyContractNo, PersonId, ApartmentId),
  CONSTRAINT FK_RENTS_APARTMENT
    FOREIGN KEY (ApartmentId)
    REFERENCES vsisp41.Apartment (EstateId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_RENTS_PERSON
    FOREIGN KEY (PersonId)
    REFERENCES vsisp41.Person (Id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_RENTS_TENANCYCONTRACT
    FOREIGN KEY (TenancyContractNo)
    REFERENCES vsisp41.TenancyContract (ContractNo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Data for table vsisp41.EstateAgent
-- -----------------------------------------------------
INSERT INTO vsisp41.EstateAgent (EstateAgentId, Name, Address, Login, Password) VALUES (1, 'Chuck Norris', 'USA', 'derChuck', 'passwort123');
INSERT INTO vsisp41.EstateAgent (EstateAgentId, Name, Address, Login, Password) VALUES (2, 'Friderike Müller', 'DE', 'dieFriderike', 'passwort123');


-- -----------------------------------------------------
-- Data for table vsisp41.Estate
-- -----------------------------------------------------
INSERT INTO vsisp41.Estate (Id, EstateAgentId, City, PostalCode, Street, StreetNumber, SquareArea) VALUES (1, 1, 'Hamburg', '22222', 'Tolle Straße', '123', 100);
INSERT INTO vsisp41.Estate (Id, EstateAgentId, City, PostalCode, Street, StreetNumber, SquareArea) VALUES (2, 1, 'Friedrichsheim', '33333', 'Noch tollere Straße', '321', 3000);
INSERT INTO vsisp41.Estate (Id, EstateAgentId, City, PostalCode, Street, StreetNumber, SquareArea) VALUES (3, 2, 'Berlin', '13133', 'Der tolle Weg', '231', 4);
INSERT INTO vsisp41.Estate (Id, EstateAgentId, City, PostalCode, Street, StreetNumber, SquareArea) VALUES (4, 2, 'München', '99999', 'Beim tollem Brauhausweg', '666', 42);


-- -----------------------------------------------------
-- Data for table vsisp41.Apartment
-- -----------------------------------------------------
INSERT INTO vsisp41.Apartment (EstateId, Floor, Rent, Rooms, Balcony, BuiltInKitchen) VALUES (1, 5, 750, 1, 1, 1);
INSERT INTO vsisp41.Apartment (EstateId, Floor, Rent, Rooms, Balcony, BuiltInKitchen) VALUES (3, 0, 17.5, 1, 0, 0);


-- -----------------------------------------------------
-- Data for table vsisp41.House
-- -----------------------------------------------------
INSERT INTO vsisp41.House (EstateId, Floors, Price, Garden) VALUES (2, 3, 1000000, 1);
INSERT INTO vsisp41.House (EstateId, Floors, Price, Garden) VALUES (4, 2, 700000, 0);

