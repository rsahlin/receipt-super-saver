﻿# receipt-super-saver

I have a large family for 4 boys and spend a lot of my income on food and household related purchases.
Mostly these are done in one of the major supermarket chains in Sweden. 
As I am using my membership to get discounts I have known for a long time that they keep track of quite detailed purchase information.
Online I can do stuff like manage purchase lists, check discounts and the like.
However there is no function that lists all my purchases based on category or item!

- This information would be great in order to try to reduce overall cost. 
It's common prioritization really - check where most of the money are spent and try to reduce this.

Sadly no luck with this online feature......

Until GDPR kicked into effect forcing the retailer to allow me to get a look at all the data they keep - this includes purchase history.

Said and done, I requested this data and created a simple framework for importing and converting this data into a common format.
The project is written in Java and does not rely on any database for portability.
Import into your favourite IDE using pom.xml

Currently there is support for ICA receipt data (made up of 2 files)
The input data needs to be in JSON, so the first step is to convert from XML. 
Remove top level root when converting. I used this converter:
http://convertjson.com/xml-to-json.htm

- Currently there is no command line option to configure the app.

Next step is to put json files somewhere on the filesystem and point the main program to it.
Then recompile and run your app.

How to request receipt data from ICA.

- You must have an online login, this is usually done using 'Mobilt BankID'.
- Login to your account.
- Go to 'Inställningar' by selecting your account (name)
- Select 'Mina data' and then 'Hantera Dina Data'
- Click 'Portera din data' and sign the request - you will get a download linke sent to your email within a day or two.

- Once you get the download link, download data to your computer then convert xml files to json using:
http://convertjson.com/xml-to-json.htm
- Use option 1: Choose an xml file and locate 'Butik_kvitto.xml'
- On the right hand side select option 'Remove top-level root' then click 'Convert xml to json'
- In the 'Save your result' box type 'Butik_kvitto' then click to download result.
* Repeat the above steps with 'Butik_kvittorader.xml' - saving this to 'Butik_kvittorader'

Put the files in a known directory - then run the app.

How to request receipt data from Coop.

- You must have an online login, usually done using email then connect your membership id with your account.
- Login to your account.
- At the bottom of the start page you will find 'Kundservice' - click 'Personuppgifter' below.
- Scroll down to the link: "Information till dig som är kund hos Coop Online och handlar i vår butik på nätet" and click it.
- Scroll down to the link: "Blankett för registerutdrag eller radering (pdf)" and click it.
- This will open a PDF document, print it, fill in your name, personnummer then sign.
- Mark the checkbox "Jag vill veta om Coop Online behandlar personuppgifter om mig...." and
"på ett USB-minne"
- Either post (snail-mail) or scan/photograph and email to the adress mentioned in the document.


How to request receipt data from City-Gross.

- I am unsure how to do this, on their website they say you should call customer support.
This seems a bit unnecessary and I will try to mail them.





