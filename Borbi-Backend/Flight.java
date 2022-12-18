package javasrc.model;
public class Flight {

        protected int flightId;
        protected int startClockId;
        protected int endClockId;
        protected String flightDate;
        protected String flightHour;
        protected String flightTime;
        protected int companyId;
        protected int planeId;
        protected double ticketPayment;
        protected String companyName;
        protected String startClock;
        protected String endClock;

        public Flight() {
        }

        public Flight(int flightId, int startClockId, int endClockId, String flightDate, String flightHour, String flightTime, int companyId, int planeId, double ticketPayment) {
            this.flightId= flightId;
            this.startClockId = startClockId;
            this.endClockId = endClockId;
            this.flightDate= flightDate;
            this.flightHour= flightHour;
            this.flightTime = flightTime;
            this.companyId = companyId;
            this.planeId = planeId;
            this.ticketPayment = ticketPayment;
        }

        public Flight(int startClockId, int endClockId, String flightDate, String flightTime, int companyId, int planeId, double ticketPayment) {
            this.startClockId = startClockId;
            this.endClockId = endClockId;
            this.flightDate = flightDate;
            this.flightTime= flightTime;
            this.companyId = companyId;
            this.planeId = planeId;
            this.ticketPayment = ticketPayment;
        }

        public Flight(int flightId, String flightDate, String flightHour, String flightTime, double ticketPayment, String companyName, String startClock, String endClock) {
            this.flightId = flightId;
            this.flightDate = flightDate;
            this.flightHour = flightHour;
            this.flightTime = flightTime;
            this.ticketPayment = ticketPayment;
            this.companyName = companyName;
            this.startClock = startClock;
            this.endClock = endClock;
        }


        public int getFlightId() {
            return flightId;
        }

        public void setFlightId(int flightId) {
            this.flightId = flightId;
        }

        public int getStartClockId() {
            return startClockId;
        }

        public void setStartClockId(int startClockId) {
            this.startClockId = startClockId;
        }

        public int getEndClockId() {
            return endClockId;
        }

        public void setEndClockId(int endClockId) {
            this.endClockId= endClockId;
        }

        public String getFlightDate() {
            return flightDate;
        }

        public void setFlightDate(String flightDate) {
            this.flightDate = flightDate;
        }

        public String getFlightHour() {
            return flightHour;
        }

        public void setFlightHour(String flightHour) {
            this.flightHour = flightHour;
        }

        public String getFlightTimet() {
            return flightTime;
        }

        public void setFlightTime(String flightTime) {
            this.flightTime = flightTime;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getPlaneId() {
            return planeId;
        }

        public void setPlaneId(int planeId) {
            this.planeId = planeId;
        }

        public double getTicketPayment() {
            return ticketPayment;
        }

        public void setTicketPayment(double ticketPayment) {
            this.ticketPayment = ticketPayment;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getStartClock() {
            return startClock;
        }

        public void setStartClock(String startClock) {
            this.startClock= startClock;
        }

        public String getEndClock() {
            return endClock;
        }

        public void setEndClock(String endClock) {
            this.endClock = endClock;
        }

    }
