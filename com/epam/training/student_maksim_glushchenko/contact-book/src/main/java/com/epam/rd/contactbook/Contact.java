package com.epam.rd.contactbook;

public class Contact {
    private final static int NAME_COUNT =1;
    private final static int PHONE_COUNT =1;
    private final static int EMAILS_COUNT =3;
    private final static int SOCIALS_COUNT =5;

    private final static int NAME_INDEX =0;
    private final static int PHONE_INDEX =1;
    private final static int EMAILS_START_INDEX =2;
    private final static int SOCIALS_START_INDEX =5;


    private class NameContactInfo implements ContactInfo{
        public NameContactInfo(String contactName){
            name=contactName;
        }
        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return name;
        }
    }

    static public class Email implements ContactInfo{
        private final String localPart;
        private final String domain;

        public Email(String localPart, String domain){
            this.localPart=localPart;
            this.domain=domain;
        }
        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return localPart+"@"+domain;
        }
    }
    public static class Social implements ContactInfo{
        private String title;
        private String id;
        public Social(String title, String id){
            this.title=title;
            this.id=id;
        }
        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getValue() {
            return id;
        }
    }
    private String name;
    private final ContactInfo[] entries = new ContactInfo[NAME_COUNT+PHONE_COUNT+EMAILS_COUNT+SOCIALS_COUNT];
    private int emailCounter;
    private int socialCounter;
    public Contact(String contactName) {
        entries[NAME_INDEX]=new NameContactInfo(contactName);
    }

    public void rename(String newName) {
        if(newName!=null && !newName.isBlank()){
            name=newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        if(emailCounter>=EMAILS_COUNT){
            return null;
        }
        Email emailNew=new Email(localPart, domain);
        entries[EMAILS_START_INDEX+emailCounter]=emailNew;
        emailCounter++;
        return emailNew;
    }


    public Email addEpamEmail(String firstname, String lastname) {
        if(emailCounter>=EMAILS_COUNT){
            return null;
        }
        Email epamEmail = new Email(firstname+"_"+lastname, "epam.com"){
            @Override
            public String getTitle() {
                return "Epam Email";
            }
        };

        entries[EMAILS_START_INDEX+emailCounter]=epamEmail;
        emailCounter++;
        return epamEmail;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if(entries[PHONE_INDEX]!=null){
            return  null;
        }
        String phone="+"+code+" "+number;
        ContactInfo phoneNumber= new ContactInfo() {
            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                return phone;
            }
        };
        entries[PHONE_INDEX]=phoneNumber;
        return phoneNumber;
    }

    public Social addTwitter(String twitterId) {
        return addSocialMedia("Twitter", twitterId);

    }

    public Social addInstagram(String instagramId) {
        return addSocialMedia("Instagram", instagramId);
    }

    public Social addSocialMedia(String title, String id) {
        if(socialCounter>=SOCIALS_COUNT){
            return  null;
        }
        Social socNew= new Social(title, id);
        entries[SOCIALS_START_INDEX+socialCounter]= socNew;
        socialCounter++;
        return socNew;
    }

    public ContactInfo[] getInfo() {
        int addedInfoNumber = 1 + emailCounter + socialCounter;
        if(entries[PHONE_INDEX]!=null){
            addedInfoNumber++;
        }
        ContactInfo[] addedInfo = new ContactInfo[addedInfoNumber];

        int i = 0;
        for (ContactInfo info : entries) {
            if (info != null) {
                addedInfo[i++] = info;
            }
        }

        return addedInfo;
    }

}
