package javasrc.model;
public class AboutCompany{
        protected int companyId;
        protected String companyName;

        public AboutCompany() {
        }

        public AboutCompany(String companyName) {
            this.companyName = companyName;
        }

        public AboutCompany(int companyId) {
            this.companyId = companyId;
        }

        public AboutCompany(int companyId, String companyName) {
            this.companyId = companyId;
            this.companyName = companyName;
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
