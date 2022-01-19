USE paymybuddysql;

DROP TABLE IF EXISTS Wallet;
DROP TABLE IF EXISTS Contact;
DROP TABLE IF EXISTS Transaction;
DROP TABLE IF EXISTS Profile;
DROP TABLE IF EXISTS User;

CREATE TABLE User;

CREATE TABLE Profile (
ProfileID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
LastName VARCHAR(100),
FirstName VARCHAR(100),
UserID INTEGER NOT NULL
#FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

CREATE TABLE Wallet (
Amount INTEGER,
UserID INTEGER NOT NULL
#FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);

CREATE TABLE Contact (
UserID INTEGER NOT NULL,
#FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
ProfileID INTEGER NOT NULL,
FOREIGN KEY (UserID) REFERENCES Profile(ProfileID) ON DELETE CASCADE
);

CREATE TABLE Transaction (
DateTime DATETIME(6) NOT NULL,
Amount INTEGER NOT NULL,
SenderID INTEGER NOT NULL,
RecipientID INTEGER NOT NULL
#FOREIGN KEY (SenderID) REFERENCES User(UserID) ON DELETE CASCADE,
#FOREIGN KEY (RecipientID) REFERENCES User(UserID) ON DELETE CASCADE
);