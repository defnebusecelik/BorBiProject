package javasrc.model;
public class AboutFlight {
        protected int flightId;
        protected int seat;
        protected int companyId;
        protected String companyName;
        protected String airline;

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

        public int getFlightId() {
            return flightId;
        }

        public void setFlightId(int flightId) {
            this.flightId = flightId;
        }

        public String getAirline() {
        return airline;
        }

        public void setAirline(String airline) {
        this.airline = airline;
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
