package com.rent.tool.util;


public class Constants {
	public static final String MESSAGE = "message";
	public static final String STATUS = "status";
	public static final String VERIFIED = "verified";
	public final static String AA_DATA = "aaData";
	public final static String DATA = "data";
	public final static String TOTAL_DISPLAY_RECORD = "iTotalDisplayRecords";
	public final static String TOTAL_RECORD = "iTotalRecords";
	public final static String DELETED = "RETURNED";
	
	public final static String PROCESSD = "PROCESSD";
	public final static String RETURNED = "RETURNED";
	public static final String FAILED = "FAILED";
	public static final String SUCCESS = "SUCCESS";
	public final static String FILE_NAME = "fileName";
	public final static String TYPE = "type";
	public final static String NEW_OBJ = "newObj";
	public final static String OLD_OBJ = "oldObj";
	public final static String NULL = "null";
	public final static String COUNT_BY_STATUS = "countByStatus";
	public final static String NAME= "name";
	public final static String COUNT= "count";

	public static class SERVICES {
		public static final String PAYROLL = "PAYROLL";
		public static final String STATEMENT = "Statement";
		public static final String PAYMENT = "payment";
		public static final String BILL_PAY = "bilpay";
		public static final String DEFAULT = "Default";
	}

	public static class MESSAGE_STATUS {
		public static final String VERIFIED = "VERIFIED";
		public static final String PROCESSED = "PROCESSD";
		public static final String SUCCESS = "SUCCESS";
		public static final String RETURNED = "RETURNED";
		public static final String FAILED = "FAILED";
		public static final String DRAFT = "DRAFT";
		public static final String GENERATED = "GENERATED";
		public static final String RECEIVED = "RECEIVED";
		public static final String REPAIR = "REPAIR";
		public static final String NEW = "NEW";
	}

	public static class USERLOG {
		// security user log
		public static final String USER_LOG_SHEET_NAME = "Login Details Report";
		public static final String USER_LOG_MSG_USER_ID = "User ID";
		public static final String USER_LOG_MSG_LOGIN_TIME = "Login Time";
		public static final String USER_LOG_MSG_LOGIN_STATUS = "Login Status";
		public static final String USER_LOG_MSG_REASON = "Reason";
		public static final String USER_LOG_MSG_IP_ADDRESS = "IP Address";
		public static final String USER_LOG_MSG_SYSTEM_DATE = "System Date";
		public static final String USER_LOG_XLS_FILE_NAME = "User_Log.xls";
		public static final String USER_LOG_PDF_NAME = "Log Report";
		public static final String USER_LOG_PDF_FILE_NAME = "UserLog.pdf";
		public static final String USER_LOG_FAIL = "Fail";
		public static final String USER_LOG_S = "S";
		public static final String USER_LOG_SUCCESS = "Success";
		public static final String USER_LOG_USER_ID = "userId";
		public static final String USER_LOG_LOG_TIME = "logTime";
		public static final String USER_LOG_REASON = "reason";
		public static final String USER_LOG_STATUS = "logStatus";
		public static final String USER_LOG_SYSTEM_DATE = "sysDate";
		public static final String USER_LOG_IP_ADDRESS = "ipAddress";
		public static final String USER_LOG_SUCCESSFUL = "Successful";
		public static final String USER_LOG_FAILURE = "Failure";
		public static final String USER_LOG_DATE_RANGE = "dateRange";
		public static final String USER_LOG_PK = "pk";
	}

	public static class GROUP {
		// security group
		public static final String GROUP_ID = "groupId";
		public static final String GROUP_NAME = "groupName";
		public static final String GROUP_HOME_SCREEN = "homeScreen";
		public static final String GROUP_TYPE = "groupType";
		public static final String GROUP_STATUS = "status";
		public static final String GROUP_HOME_URL = "homeUrl";
		public static final String GROUP_EXIST = "Group already exist.";
		public static final String GROUP_CREATED = "Group created successfully.";
		public static final String GROUP_CREATION_FAILED = "Group creation failed.";
		public static final String GROUP_NOT_EXIST = "Group doesn't exist.";
		public static final String GROUP_UPDATED = "Group updated successfully.";
		public static final String GROUP_UPDATE_FAILED = "Group update failed.";
		public static final String GROUP_DELETED = "Group Deleted successfully.";
		public static final String GROUP_DELETE_FAILED = "Group Deletion failed.";
		public static final String GROUP_VERIFIED = "Group Verified successfully.";
		public static final String GROUP_VERIFICATION_FAILED = "Group verification failed.";
		public static final String GROUP_REACTIVATED = "Group Re-Activation successfull.";
		public static final String GROUP_REACTIVATION_FAILED = "Group Re-Activation failed.";
		public static final String GROUP_ALREADY_ACTIVATED = "Group is already Active.";
	}

	public static class PERMISSION {
		// security permission
		public static final String PERMISSION_SCREEN_TYPE = "screenType";
		public static final String PERMISSION_SCREEN_ID = "screenId";
		public static final String PERMISSION_SCREEN_NAME = "screenName";
		public static final String PERMISSION_ADD_FLAG = "addFlag";
		public static final String PERMISSION_UPDATE_FLAG = "updateFlag";
		public static final String PERMISSION_DISPLAY_FLAG = "disFlag";
		public static final String PERMISSION_DELETE_FLAG = "delFlag";
		public static final String PERMISSION_VERIFY_FLAG = "verFlag";
		public static final String PERMISSION_CATEGORY_NAME = "categoryName";
		public static final String PERMISSION_CATEGORY_NO = "categoryNo";
		public static final String PERMISSION_NO = "N";
		public static final String PERMISSION_YES = "Y";
		public static final String PERMISSION_GROUP_ID_REGEX_MSG = "Group ID shouldn't contains space or any invalid character";
		public static final String PERMISSION_SCREEN_CREATED = "Screen For Group Created";
		public static final String PERMISSION_GROUP_EXIST = "Already Group is Exist in this Group ID";
		public static final String PERMISSION_GROUP_ARRAY = "groupArr";
		public static final String PERMISSION_DEFAULT_CLIENT_ID = "999";
		public static final String PERMISSION_FUNCTION_FLAG = "A";
	}

	public static class USER {
		// security user
		public static final String USER_USER_ID = "userId";
		public static final String USER_GROUP_ID = "groupId";
		public static final String USER_GROUP_NAME = "groupName";
		public static final String USER_USER_NAME = "userName";
		public static final String USER_STATUS = "status";
		public static final String USER_EMAIL_ID = "emailId";
		public static final String USER_CELLPHONE = "cellPhone";
		public static final String USER_NOT_EXIST = "No user record exist for given id";
		public static final String USER_EXIST = "User already exist.";
		public static final String USER_ADMINV = "ADMINV";
		public static final String USER_CRO = "CRO";
		public static final String USER_MSG_USER_TYPE = "S";
		public static final String USER_CREATED = "User created successfully.";
		public static final String USER_CREATION_FAILED = "User creation failed.";
		public static final String USER_UPDATED = "User updated successfully.";
		public static final String USER_UPDATE_FAILED = "User update failed.";
		public static final String USER_DELETED = "User deleted successfully.";
		public static final String USER_DELETION_FAILED = "User deleted failed.";
		public static final String USER_VERIFIED = "Verified Successfully. ";
		public static final String USER_VERIFICATION_FAILED = " Verification failed.";
		public static final String USER_NO_RECORD = "No record to verify";
		public static final String USER_USER_TYPE = "userType";
		public static final String USER_CATEGORY = "userCategory";
	}

	
}