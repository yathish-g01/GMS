package com.epam.utility;

import com.epam.configuration.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiBaseClass {
    static ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    public static Logger logger = LogManager.getLogger(ApiBaseClass.class.getName());

    protected static RequestSpecification requestSpecification;
    protected static String baseURI=configurationManager.getProperty("baseURI");

    public static void initRequest() {
        logger.info("Setting the base URI for the API");
        requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(baseURI).build();
        logger.info("Base URI is set as " + baseURI);
    }
}
