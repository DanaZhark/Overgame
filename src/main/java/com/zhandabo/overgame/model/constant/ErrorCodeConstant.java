package com.zhandabo.overgame.model.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodeConstant {

    public static final String COMPANY_NON_EXISTENT = "FEEDBACK_CORE_COMPANY_NON_EXISTENT";
    public static final String KEYCLOAK_USER_NOT_FOUND = "FEEDBACK_CORE_KEYCLOAK_USER_NOT_FOUND";
    public static final String KEYCLOAK_USER_TOKEN_RESPONSE_ERROR = "KEYCLOAK_USER_TOKEN_RESPONSE_ERROR";
    public static final String PLACE_ID_NON_EXISTENT = "PLACE_ID_NON_EXISTENT";
    public static final String REVIEW_ID_NON_EXISTENT = "REVIEW_ID_NON_EXISTENT";
    public static final String FILE_UPLOAD_ERROR = "FILE_UPLOAD_ERROR";
    public static final String WRONG_NUMBER_FORMAT = "WRONG_NUMBER_FORMAT";
    public static final String INSUFFICIENT_PERMISSION = "INSUFFICIENT_PERMISSION";
    public static final String USER_EMAIL_ALREADY_EXIST = "USER_EMAIL_ALREADY_EXIST";
    public static final String KEYCLOAK_USER_NOT_EXIST = "USER_NOT_EXIST";
    public static final String CREATE_KEYCLOAK_USER_ERROR = "CREATE_KEYCLOAK_USER_ERROR";
    public static final String MAILING_ERROR = "MAILING_ERROR";
    public static final String PASSWORD_MISMATCH = "PASSWORD_MISMATCH";
    public static final String SUPER_ADMIN_NO_ACCEPTABLE = "SUPER_ADMIN_NO_ACCEPTABLE";
    public static final String QR_GENERATING_ERROR = "QR_GENERATING_ERROR";
    public static final String CODE_PLACES_GROUP_ID_UNIQUE = "PLACE_WITH_THIS_CODE_AND_PLACES_GROUP_ID_IS_UNIQUE_CONSTRAINT";
    public static final String PLACES_GROUP_ID_IS_NOT_PRESENT = "PLACES_GROUP_ID_IS_NOT_PRESENT";
    public static final String COMPANY_ID_NOT_FOUND = "COULD_NOT_FIND_A_COMPANY_WITH_THIS_ID";
    public static final String THE_GIVEN_ID_DOES_NOT_EXISTS = "THE_GIVEN_ID_DOES_NOT_EXISTS";
    public static final String REVIEW_NPS_ID_NON_EXISTENT = "REVIEW_NPS_ID_NON_EXISTENT";
    public static final String NETWORK_ID_NON_EXISTENT = "NETWORK_ID_NON_EXISTENT";
}
