package com.company;

/**
 * Created by rdw1995 on 9/26/16.
 */
public class Country {

        String nameID;
        String name;

        public String getNameID() {
            return nameID;
        }

        public void setNameID(String nameID) {
            this.nameID = nameID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Country (String nameID, String name) {
            this.nameID = nameID;
            this.name = name;

        }

        @Override
        public String toString () {
            return "Country: (nameID -"  + '\'' + " , name -" + name + '\'' + ") :D";
        }
}

