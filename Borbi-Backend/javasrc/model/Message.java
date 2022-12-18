package javasrc.model;
public class Message {
        protected int messageId;
        protected String messageName;
        protected String messageMail;
        protected String messageAbout;
        protected String messageContent;
        protected String messageDate;

        public Message() {
        }

        public Message(String messageName, String messageMail, String messageAbout, String messageContent) {
            this.messageName = messageName;
            this.messageMail = messageMail;
            this.messageAbout = messageAbout;
            this.messageContent = messageContent;
        }
        public Message(int messageId, String messageName, String messageMail, String messageAbout, String messageContent, String messageDate) {
            this.messageId = messageId;
            this.messageName = messageName;
            this.messageMail = messageMail;
            this.messageAbout = messageAbout;
            this.messageContent = messageContent;
            this.messageDate = messageDate;
        }

        public int getMessageId() {
            return messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }

        public String getMessageName() {
            return messageName;
        }

        public void setMessageName(String messageName) {
            this.messageName = messageName;
        }

        public String getMessageMail() {
            return messageMail;
        }

        public void setMessageMail(String messageMail) {
            this.messageMail = messageMail;
        }

        public String getMessageAbout() {
            return messageAbout;
        }

        public void setMessageAbout(String messageAbout) {
            this.messageAbout = messageAbout;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public String getMessageDate() {
            return messageDate;
        }

        public void setMessageDate(String messageDate) {
            this.messageDate = messageDate;
        }
    }
