package javasrc.model;
public class Country {
        protected int airlineCountryId;
        protected String airlineCountryName;

        public Country() {
        }

        public Country(String airlineCountryName) {
            this.airlineCountryName = airlineCountryName;
        }

        public Country(int airlineCountryId, String airlineCountryName) {
            this.airlineCountryId = airlineCountryId;
            this.airlineCountryName = airlineCountryName;
        }

        public int getAirlineCountryId() {
            return airlineCountryId;
        }

        public void setAirlineCountryId(int airlineCountryId) {
            this.airlineCountryId = airlineCountryId;
        }

        public String getAirlineCountryName() {
            return airlineCountryName;
        }

        public void setAirlineCountryName(String airlineCountryName) {
            this.airlineCountryName = airlineCountryName;
        }

    }
