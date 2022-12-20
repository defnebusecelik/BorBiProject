package javasrc.model;
public class AboutFlight {
        protected int flightId;
        protected int seat;
        protected int companyId;
        protected String companyName;
        protected String airlineName;

        public AboutFlight() {
        }

        public AboutFlight(int flightId) {
            this.flightId = flightId;
        }

        public AboutFlight(int flightId, int companyId) {
            this.flightId = flightId;
            this.companyId = companyId;
        }

        public AboutFlight(int flightId, int seat, int companyId) {
            this.flightId = flightId;
            this.seat = seat;
            this.companyId = companyId;
        }

        public AboutFlight(int flightId, int seat, String companyName) {
            this.flightId = flightId;
            this.seat = seat;
            this.companyName =companyName;
        }

        public AboutFlight(int flightId ,String companyName ,int seat,int companyId){
            this.flightId=flightId;
            this.companyName=companyName;
            this.seat=seat;
            this.companyId=companyId;
        }

        public int getFlightId() {
            return flightId;
        }

        public void setFlightId(int flightId) {
            this.flightId = flightId;
        }

        public String getAirlineName() {
        return airlineName;
        }

        public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
