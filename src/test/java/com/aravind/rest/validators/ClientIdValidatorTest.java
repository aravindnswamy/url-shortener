//package com.aravind.rest.validators;
//
//import com.aravind.rest.dao.IDataStore;
//import com.aravind.rest.exceptions.BusinessException;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Matchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
///**
// * A Unit test class for {@link ClientIdValidator}
// *
// * @author arvind.n
// */
//@RunWith(MockitoJUnitRunner.class)
//public class ClientIdValidatorTest {
//
//    private static final String DUMMY_CLIENT_ID = "clientId";
//
//    @Mock
//    private IDataStore dataStore;
//
//    @InjectMocks
//    private ClientIdValidator clientIdValidator;
//
//    @Test(expected = BusinessException.class)
//    public void test_validate_invalidClientId_throwsBusinessException() {
//        Mockito.when(dataStore.checkKeyExists(Mockito.anyString())).thenThrow(BusinessException.class);
//        dataStore.checkKeyExists(DUMMY_CLIENT_ID);
//    }
//
//    @Test(expected = BusinessException.class)
//    public void test_validate_validClientId_throwsBusinessException() {
//        Mockito.when(dataStore.checkKeyExists(Mockito.anyString())).thenReturn(Matchers.eq(true));
//        Assert.assertTrue(dataStore.checkKeyExists(DUMMY_CLIENT_ID));
//    }
//}
