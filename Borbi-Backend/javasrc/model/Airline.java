package javasrc.model;
public class Airline {
        protected String airlineId;
        protected int airlineCountryId;
        protected int airlineCityId;
        protected String airlineCountryName;
        protected String airlineCityName;
        protected String airlineName;

        public Airline() {
        }

        public Airline(String airlineId, String airlineName) {
            this.airlineId = airlineId;
            this.airlineName = airlineName;
        }

        public Airline(int airlineCountryId, int airlineCityId, String airlineName) {
            this.airlineCountryId = airlineCountryId;
            this.airlineCityId= airlineCityId;
            this.airlineName = airlineName;
        }

        public Airline(String airlineId,int airlineCountryId, int airlineCityId) {
            this.airlineId=airlineId;
            this.airlineCountryId = airlineCountryId;
            this.airlineCityId= airlineCityId;
    }

        public Airline(String airlineId, int airlineCountryId, int airlineCityId, String airlineName) {
            this.airlineId = airlineId;
            this.airlineCountryId = airlineCountryId;
            this.airlineCityId = airlineCityId;
            this.airlineName = airlineName;
        }

        public Airline(int id, int airlineCountryId, int airlineCityId, String airlineName,String airlineId) {
            this.airlineCountryId = airlineCountryId;
            this.airlineCityId = airlineCityId;
            this.airlineName = airlineName;
            this.airlineId = airlineId;
        }

        public Airline(String airlineId, int airlineCountryId, int airlineCityId, String airlineName,String airlineCountryName, String airlineCityName) {
            this.airlineId = airlineId;
            this.airlineCountryId = airlineCountryId;
            this.airlineCityId = airlineCityId;
            this.airlineName = airlineName;
            this.airlineCountryId=airlineCountryId;
            this.airlineCityId=airlineCityId;
        }


        public String getAirlineId() {
            return airlineId;
        }

        public void setAirlineId(String airlineId) {
            this.airlineId = airlineId;
        }

        public int getAirlineCountryId() {
            return airlineCountryId;
        }

        public void setAirlineCountryId(int airlineCountryId) {
            this.airlineCountryId = airlineCountryId;
        }

        public int getAirlineCityId() {
            return airlineCityId;
        }

        public void setAirlineCityId(int airlineCityId) {
            this.airlineCityId = airlineCityId;
        }

        public String getAirlineCountryName() {
            return airlineCountryName;
        }

        public void setAirlineCountryName(String airlineCountryName) {
            this.airlineCountryName = airlineCountryName;
        }

        public String getAirlineCityName() {
            return airlineCityName;
        }

        public void setAirlineCityName(String airlineCityName) {
            this.airlineCityName = airlineCityName;
        }

        public String getAirlineName() {
            return airlineName;
        }

        public void setAirlineName(String airlineName) {
            this.airlineName = airlineName;
        }
    }
