package javasrc.model;
public class Airline {
        protected String airlineId;
        protected int countryId;
        protected int cityId;
        protected String airlineName;

        public Airline() {
        }

        public Airline(String airlineId, String airlineName) {
            this.airlineId = airlineId;
            this.airlineName = airlineName;
        }

        public Airline(int countryId, int cityId, String airlineName) {
            this.countryId = countryId;
            this.cityId= cityId;
            this.airlineName = airlineName;
        }

        public Airline(String airlineId,int countryId, int cityId) {
            this.airlineId=airlineId;
            this.countryId = countryId;
            this.cityId= cityId;
    }

        public Airline(String airlineId, int countryId, int cityId, String airlineName) {
            this.airlineId = airlineId;
            this.countryId = countryId;
            this.cityId = cityId;
            this.airlineName = airlineId;
        }


        public String getAirlineId() {
            return airlineId;
        }

        public void setAirlineId(String airlineId) {
            this.airlineId = airlineId;
        }

        public int getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getAirlineName() {
            return airlineName;
        }

        public void setAirlineName(String airlineName) {
            this.airlineName = airlineName;
        }

    }
