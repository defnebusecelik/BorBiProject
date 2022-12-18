package javasrc.model;
public class Reservation {

        protected int reservationId;
        protected String reservationDate;
        protected String passengerNameRecord;
        protected String customerName;
        protected String customerLastName;
        protected String customerMail;
        protected String customerPhone;
        protected int versionNumber;
        protected String seatNumber;
        protected int customerId;
        protected String sourceAirlineId;
        protected String destinationAirlineId;
        protected String flightDate;
        protected int adultNumber;
        protected int childNumber;
        protected int flightId;
        protected String sourceCity;
        protected String sourceCityId;
        protected String destinationCity;
        protected String destinationCityId;
        protected String flightStartHour;
        protected String flightEndHour;
        protected String flightTime;
        protected String companyName;
        protected Double ticketPayment;
        protected String planeId;
        protected int totalSeat;
        protected int fullSeat;
        protected int caseSeat;

        public Reservation() {
        }

        public Reservation(String passengerNameRecord, String customerName ,String customerLastName) {
            this.passengerNameRecord = passengerNameRecord;
            this.customerName=customerName;
            this.customerLastName = customerLastName;
    }

        public Reservation(int adultNumber, int childNumber) {
            this.adultNumber = adultNumber;
            this.childNumber = childNumber;
    }

        public Reservation(int reservationId, String customerName, String customerLastName, String customerMail, String customerPhone, String reservationDate) {
            this.reservationId = reservationId;
            this.customerName = customerName;
            this.customerLastName = customerLastName;
            this.customerMail = customerMail;
            this.customerPhone = customerPhone;
            this.reservationDate = reservationDate;
        }

        public Reservation(int reservationId, String reservationDate, String passengerNameRecord, String customerLastName) {
            this.reservationId = reservationId;
            this.reservationDate = reservationDate;
            this.passengerNameRecord = passengerNameRecord;
            this.customerLastName = customerLastName;
        }

        public Reservation(String flightDate, String sourceCity, String sourceAirlineId, String sourceCityId, String destinationCity, String destinationAirlineId, String destinationCityId, String companyName,String planeId) {
            this.flightDate = flightDate;
            this.sourceCity = sourceCity;
            this.sourceAirlineId = sourceAirlineId;
            this.sourceCityId = sourceCityId;
            this.destinationCity = destinationCity;
            this.destinationAirlineId = destinationAirlineId;
            this.destinationCityId = destinationCityId;
            this.companyName = companyName;
            this.planeId = planeId;
    }

        public Reservation(String passengerNameRecord, String companyName,String customerName, String customerLastName, String customerMail, String customerPhone, int versionNumber, String seatNumber, int customerId, int flightId) {
            this.passengerNameRecord = passengerNameRecord;
            this.companyName=companyName;
            this.customerName = customerName;
            this.customerLastName = customerLastName;
            this.customerMail = customerMail;
            this.customerPhone = customerPhone;
            this.versionNumber = versionNumber;
            this.seatNumber = seatNumber;
            this.customerId = customerId;
            this.flightId = flightId;
        }

        public Reservation(int reservationId, String reservationDate, String passengerNameRecord, String customerName, String customerLastName, String customerMail, String customerPhone, int versionNumber, String seatNumber, int customerId, int flightId) {
            this.reservationId = reservationId;
            this.reservationDate = reservationDate;
            this.passengerNameRecord = passengerNameRecord;
            this.customerName = customerName;
            this.customerLastName = customerLastName;
            this.customerMail = customerMail;
            this.customerPhone = customerPhone;
            this.versionNumber = versionNumber;
            this.seatNumber = seatNumber;
            this.customerId = customerId;
            this.flightId = flightId;
        }

        public Reservation(String flightDate, int flightId, String sourceCity, String sourceAirlineId, String sourceCityId, String destinationCity, String destinationAirlineId, String destinationCityId,String flightTime, String companyName, String flightStartHour, String flightEndHour) {
            this.flightDate = flightDate;
            this.flightId = flightId;
            this.sourceCity = sourceCity;
            this.sourceAirlineId = sourceAirlineId;
            this.sourceCityId = sourceCityId;
            this.destinationCity = destinationCity;
            this.destinationAirlineId = destinationAirlineId;
            this.destinationCityId = destinationCityId;
            this.flightTime = flightTime;
            this.companyName = companyName;
            this.flightStartHour = flightStartHour;
            this.flightEndHour = flightEndHour;
        }


        public Reservation(int caseSeat, int reservationId, String reservationDate, String passengerNameRecord, String customerName, String customerLastName, String customerMail, String customerPhone, int versionNumber, String seatNumber, String flightDate, String sourceCity, String sourceAirlineId, String sourceCityId, String destinationCity, String destinationAirlineId, String destinationCityId, String flightStartHour, String flightTime, String companyName, String flightEndHour, String planeId, Double ticketPayment) {
            this.caseSeat = caseSeat;
            this.reservationId= reservationId;
            this.reservationDate = reservationDate;
            this.passengerNameRecord = passengerNameRecord;
            this.customerName = customerName;
            this.customerLastName = customerLastName;
            this.customerMail = customerMail;
            this.customerPhone = customerPhone;
            this.versionNumber = versionNumber;
            this.seatNumber = seatNumber;
            this.flightDate = flightDate;
            this.sourceCity = sourceCity;
            this.sourceAirlineId = sourceAirlineId;
            this.sourceCityId = sourceCityId;
            this.destinationCity = destinationCity;
            this.destinationAirlineId= destinationAirlineId;
            this.destinationCityId = destinationCityId;
            this.flightStartHour = flightStartHour;
            this.flightTime = flightTime;
            this.companyName = companyName;
            this.flightEndHour = flightEndHour;
            this.planeId = planeId;
            this.ticketPayment = ticketPayment;
        }


        public int getCaseSeat() {
            return caseSeat;
        }

        public void setCaseSeat(int caseSeat) {
            this.caseSeat = caseSeat;
        }

        public int getFullSeat() {
            return fullSeat;
        }

        public void setFullSeat(int fullSeat) {
            this.fullSeat = fullSeat;
        }

        public int getTotalSeat() {
            return totalSeat;
        }

        public void setTotalSeat(int totalSeat) {
            this.totalSeat = totalSeat;
        }

        public int getFlightId() {
            return flightId;
        }

        public void setFlightId(int flightId) {
            this.flightId = flightId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerMail() {
            return customerMail;
        }

        public void setCustomerMail(String customerMail) {
            this.customerMail = customerMail;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public int getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(int versionNumber) {
            this.versionNumber = versionNumber;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public void setSeatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getReservationId() {
            return reservationId;
        }

        public void setReservationId(int reservationId) {
            this.reservationId = reservationId;
        }

        public String getReservationDate() {
            return reservationDate;
        }

        public void setReservationDate(String reservationDate) {
            this.reservationDate = reservationDate;
        }

        public String getPassengerNameRecord() {
            return passengerNameRecord;
        }

        public void setPassengerNameRecord(String passengerNameRecord) {
            this.passengerNameRecord = passengerNameRecord;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public void setCustomerLastName(String customerLastName) {
            this.customerLastName = customerLastName;
        }

        public String getFlightStartHour() {
            return flightStartHour;
        }

        public void setFlightStartHour(String flightStartHour) {
            this.flightStartHour = flightStartHour;
        }

        public String getFlightEndHour() {
            return flightEndHour;
        }

        public void setFlightEndHour(String flightEndHour) {
            this.flightEndHour= flightEndHour;
        }

        public String getSourceCity() {
            return sourceCity;
        }

        public void setSourceCity(String sourceCity) {
            this.sourceCity = sourceCity;
        }

        public String getSourceCityId() {
            return sourceCityId;
        }

        public void setSourceCityId(String sourceCity) {
            this.sourceCity = sourceCity;
        }

        public String getDestinationCityId() {
            return destinationCityId;
        }

        public void setDestinationCityId(String destinationCityId) {
            this.destinationCityId = destinationCityId;
        }

        public String getDestinationCity() {
            return destinationCity;
        }

        public void setDestinationCity(String destinationCity) {
            this.destinationCity = destinationCity;
        }

        public String getSourceAirlineId() {
            return sourceAirlineId;
        }

        public void setSourceAirlineId(String sourceAirlineId) {
            this.sourceAirlineId = sourceAirlineId;
        }

        public String getDestinationAirlineId() {
            return destinationAirlineId;
        }

        public void setDestinationAirlineId(String destinationAirlineId) {
            this.destinationAirlineId = destinationAirlineId;
        }

        public String getFlightTime() {
            return flightTime;
        }

        public void setFlightTime(String flightTime) {
            this.flightTime = flightTime;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Double getTicketPayment() {
            return ticketPayment;
        }

        public void setTicketPayment(Double ticketPayment) {
            this.ticketPayment = ticketPayment;
        }

        public String getFlightDate() {
            return flightDate;
        }

        public void setFlightDate(String flightDate) {
            this.flightDate = flightDate;
        }

        public int getAdultNumber() {
            return adultNumber;
        }

        public void setAdultNumber(int adultNumber) {
            this.adultNumber = adultNumber;
        }

        public int getChildNumber() {
            return childNumber;
        }

        public void setChildNumber(int childNumber) {
            this.childNumber = childNumber;
        }

        public String getPlaneId() {
            return planeId;
        }

        public void setPlaneId(String planeId) {
            this.planeId = planeId;
        }

}