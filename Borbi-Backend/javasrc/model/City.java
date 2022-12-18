package javasrc.model;
public class City {
        protected int airlineCityId;
        protected String airlineCityName;

        public City() {
        }

        public City(String airlineCityName) {
            this.airlineCityName = airlineCityName;
        }

        public City(int airlineCityId, String airlineCityName) {
            this.airlineCityId = airlineCityId;
            this.airlineCityName = airlineCityName;
        }

        public int getAirlineCityId() {
            return airlineCityId;
        }

        public void setAirlineCityId(int airlineCityId) {
            this.airlineCityId = airlineCityId;
        }

        public String getAirlineCityName() {
            return airlineCityName;
        }

        public void setAirlineCityName(String airlineCityName) {
            this.airlineCityName = airlineCityName;
        }
    }
