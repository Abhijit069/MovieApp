package com.moviebookingapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CustomerTest {
    /**
     * Method under test: {@link Customer#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new Customer()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Customer#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertTrue(customer.canEqual(customer2));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Customer#Customer()}
     *   <li>{@link Customer#setConfirmPassword(String)}
     *   <li>{@link Customer#setContactNumber(String)}
     *   <li>{@link Customer#setEmail(String)}
     *   <li>{@link Customer#setFirstName(String)}
     *   <li>{@link Customer#setLastName(String)}
     *   <li>{@link Customer#setPassword(String)}
     *   <li>{@link Customer#setRole(Set)}
     *   <li>{@link Customer#setUserName(String)}
     *   <li>{@link Customer#toString()}
     *   <li>{@link Customer#getConfirmPassword()}
     *   <li>{@link Customer#getContactNumber()}
     *   <li>{@link Customer#getEmail()}
     *   <li>{@link Customer#getFirstName()}
     *   <li>{@link Customer#getLastName()}
     *   <li>{@link Customer#getPassword()}
     *   <li>{@link Customer#getRole()}
     *   <li>{@link Customer#getUserName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Customer actualCustomer = new Customer();
        actualCustomer.setConfirmPassword("iloveyou");
        actualCustomer.setContactNumber("42");
        actualCustomer.setEmail("jane.doe@example.org");
        actualCustomer.setFirstName("Jane");
        actualCustomer.setLastName("Doe");
        actualCustomer.setPassword("iloveyou");
        HashSet<Role> role = new HashSet<>();
        actualCustomer.setRole(role);
        actualCustomer.setUserName("janedoe");
        String actualToStringResult = actualCustomer.toString();
        assertEquals("iloveyou", actualCustomer.getConfirmPassword());
        assertEquals("42", actualCustomer.getContactNumber());
        assertEquals("jane.doe@example.org", actualCustomer.getEmail());
        assertEquals("Jane", actualCustomer.getFirstName());
        assertEquals("Doe", actualCustomer.getLastName());
        assertEquals("iloveyou", actualCustomer.getPassword());
        assertSame(role, actualCustomer.getRole());
        assertEquals("janedoe", actualCustomer.getUserName());
        assertEquals(
                "Customer(firstName=Jane, lastName=Doe, userName=janedoe, email=jane.doe@example.org, password=iloveyou,"
                        + " confirmPassword=iloveyou, contactNumber=42, role=[])",
                actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Customer#Customer(String, String, String, String, String, String, String, Set)}
     *   <li>{@link Customer#setConfirmPassword(String)}
     *   <li>{@link Customer#setContactNumber(String)}
     *   <li>{@link Customer#setEmail(String)}
     *   <li>{@link Customer#setFirstName(String)}
     *   <li>{@link Customer#setLastName(String)}
     *   <li>{@link Customer#setPassword(String)}
     *   <li>{@link Customer#setRole(Set)}
     *   <li>{@link Customer#setUserName(String)}
     *   <li>{@link Customer#toString()}
     *   <li>{@link Customer#getConfirmPassword()}
     *   <li>{@link Customer#getContactNumber()}
     *   <li>{@link Customer#getEmail()}
     *   <li>{@link Customer#getFirstName()}
     *   <li>{@link Customer#getLastName()}
     *   <li>{@link Customer#getPassword()}
     *   <li>{@link Customer#getRole()}
     *   <li>{@link Customer#getUserName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Customer actualCustomer = new Customer("Jane", "Doe", "janedoe", "jane.doe@example.org", "iloveyou", "iloveyou",
                "42", new HashSet<>());
        actualCustomer.setConfirmPassword("iloveyou");
        actualCustomer.setContactNumber("42");
        actualCustomer.setEmail("jane.doe@example.org");
        actualCustomer.setFirstName("Jane");
        actualCustomer.setLastName("Doe");
        actualCustomer.setPassword("iloveyou");
        HashSet<Role> role = new HashSet<>();
        actualCustomer.setRole(role);
        actualCustomer.setUserName("janedoe");
        String actualToStringResult = actualCustomer.toString();
        assertEquals("iloveyou", actualCustomer.getConfirmPassword());
        assertEquals("42", actualCustomer.getContactNumber());
        assertEquals("jane.doe@example.org", actualCustomer.getEmail());
        assertEquals("Jane", actualCustomer.getFirstName());
        assertEquals("Doe", actualCustomer.getLastName());
        assertEquals("iloveyou", actualCustomer.getPassword());
        assertSame(role, actualCustomer.getRole());
        assertEquals("janedoe", actualCustomer.getUserName());
        assertEquals(
                "Customer(firstName=Jane, lastName=Doe, userName=janedoe, email=jane.doe@example.org, password=iloveyou,"
                        + " confirmPassword=iloveyou, contactNumber=42, role=[])",
                actualToStringResult);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");
        assertNotEquals(customer, null);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals2() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");
        assertNotEquals(customer, "Different type to Customer");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Customer#equals(Object)}
     *   <li>{@link Customer#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");
        assertEquals(customer, customer);
        int expectedHashCodeResult = customer.hashCode();
        assertEquals(expectedHashCodeResult, customer.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Customer#equals(Object)}
     *   <li>{@link Customer#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertEquals(customer, customer2);
        int expectedHashCodeResult = customer.hashCode();
        assertEquals(expectedHashCodeResult, customer2.hashCode());
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals5() {
        Customer customer = new Customer();
        customer.setConfirmPassword("Jane");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals6() {
        Customer customer = new Customer();
        customer.setConfirmPassword(null);
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals7() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("Jane");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals8() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber(null);
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals9() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("john.smith@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals10() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail(null);
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals11() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals12() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName(null);
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals13() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals14() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName(null);
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals15() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("Jane");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals16() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword(null);
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals17() {
        Role role = new Role();
        role.setRoleDescription("Jane");
        role.setRoleName("Jane");

        HashSet<Role> role2 = new HashSet<>();
        role2.add(role);

        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(role2);
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals18() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("Jane");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Method under test: {@link Customer#equals(Object)}
     */
    @Test
    void testEquals19() {
        Customer customer = new Customer();
        customer.setConfirmPassword("iloveyou");
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName(null);

        Customer customer2 = new Customer();
        customer2.setConfirmPassword("iloveyou");
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertNotEquals(customer, customer2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Customer#equals(Object)}
     *   <li>{@link Customer#hashCode()}
     * </ul>
     */
    @Test
    void testEquals20() {
        Customer customer = new Customer();
        customer.setConfirmPassword(null);
        customer.setContactNumber("42");
        customer.setEmail("jane.doe@example.org");
        customer.setFirstName("Jane");
        customer.setLastName("Doe");
        customer.setPassword("iloveyou");
        customer.setRole(new HashSet<>());
        customer.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setConfirmPassword(null);
        customer2.setContactNumber("42");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setPassword("iloveyou");
        customer2.setRole(new HashSet<>());
        customer2.setUserName("janedoe");
        assertEquals(customer, customer2);
        int expectedHashCodeResult = customer.hashCode();
        assertEquals(expectedHashCodeResult, customer2.hashCode());
    }
}

