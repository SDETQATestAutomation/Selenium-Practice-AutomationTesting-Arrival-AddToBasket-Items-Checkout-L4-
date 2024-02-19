package com.iiht.evaluation.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activities {
    public static boolean click_shop_menu(WebDriver driver) {
        WebElement shop_menu_link_element = SubActivities.find_element_use_xpath(driver, locators.arrival_add_to_basket_items_checkout_element.get("shop_menu_link"));
        if (shop_menu_link_element == null) {
            return false;
        }
        shop_menu_link_element.click();
        WebElement shop_page_nav_element = SubActivities.find_element_use_xpath(driver, locators.arrival_add_to_basket_items_checkout_element.get("shop_page_nav"));
        if (shop_page_nav_element == null) {
            return false;
        }
        boolean shop_page_nav_visible = SubActivities.wait_for_element_visible(driver, locators.arrival_add_to_basket_items_checkout_element.get("shop_page_nav"));
        System.out.println("shop_page_nav_visible " + shop_page_nav_visible);
        if (!shop_page_nav_visible) {
            return false;
        }
        String shop_page_nav_text = shop_page_nav_element.getText();
        System.out.println("shop_page_nav_text " + shop_page_nav_text);
        if (!shop_page_nav_text.contains("Shop")) {
            return false;
        }
        return true;
    }
    public static boolean click_home_menu_button(WebDriver driver) {
        WebElement home_menu_link_element = SubActivities.find_element_use_xpath(driver, locators.arrival_add_to_basket_items_checkout_element.get("home_menu_link"));
        if (home_menu_link_element == null) {
            return false;
        }
        home_menu_link_element.click();
        boolean new_arrival_div_visible = SubActivities.wait_for_element_visible(driver, locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_div"));
        System.out.println("new_arrival_div_visible " + new_arrival_div_visible);
        if (!new_arrival_div_visible) {
            return false;
        }
        return true;
    }
    public static boolean check_home_page_has_three_arrivals(WebDriver driver) {
        WebElement new_arrival_product_ul_list_element = SubActivities.find_element_use_xpath(driver, locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_ul_list"));
        if (new_arrival_product_ul_list_element == null) {
            return false;
        }
        List<WebElement> new_arrival_product_ul_list = SubActivities.find_elements_use_xpath(driver, locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_ul_list"));
        if (new_arrival_product_ul_list.isEmpty()) {
            return false;
        }
        int new_arrival_product_ul_list_size=new_arrival_product_ul_list.size();
        if(new_arrival_product_ul_list_size!=3){
            return false;
        }
        return true;
    }
    public static boolean click_product_product_image_in_arrival(WebDriver driver, String product_name) {
        String new_arrival_product_h3_text_xpath = locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_h3_text").replace("$(product_name)", product_name);
        String new_arrival_product_h3_text_link_xpath = locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_h3_text").replace("$(product_name)", product_name);
        String new_arrival_product_page_nav_xpath = locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_page_nav");

        WebElement new_arrival_product_h3_text_element = SubActivities.find_element_use_xpath(driver, new_arrival_product_h3_text_xpath);
        if (new_arrival_product_h3_text_element == null) {
            return false;
        }
        WebElement new_arrival_product_h3_text_link_element = SubActivities.find_element_use_xpath(driver, new_arrival_product_h3_text_link_xpath);
        if (new_arrival_product_h3_text_link_element == null) {
            return false;
        }
        new_arrival_product_h3_text_link_element.click();
        WebElement new_arrival_product_page_nav_element = SubActivities.find_element_use_xpath(driver, new_arrival_product_page_nav_xpath);
        if (new_arrival_product_page_nav_element == null) {
            return false;
        }
        String new_arrival_product_page_nav_element_text = new_arrival_product_page_nav_element.getText();
        System.out.println("new_arrival_product_page_nav_element_text " + new_arrival_product_page_nav_element_text);
        if(!new_arrival_product_page_nav_element_text.contains(product_name)){
            return false;
        }
        return true;
    }
    public static int get_number_of_item_in_cart(WebDriver driver){
        String new_arrival_product_summary_cart_item_detail_no_of_item_span_xpath = locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_summary_cart_item_detail_no_of_item_span");
        int number_of_item=-1;

        WebElement new_arrival_product_summary_cart_item_detail_no_of_item_span_element = SubActivities.find_element_use_xpath(driver, new_arrival_product_summary_cart_item_detail_no_of_item_span_xpath);
        String new_arrival_product_summary_cart_item_detail_no_of_item_span_element_text=new_arrival_product_summary_cart_item_detail_no_of_item_span_element.getText();
        System.out.println("new_arrival_product_summary_cart_item_detail_no_of_item_span_element_text " + new_arrival_product_summary_cart_item_detail_no_of_item_span_element_text);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(new_arrival_product_summary_cart_item_detail_no_of_item_span_element_text);
        while (matcher.find()) {
            String number = matcher.group();
            System.out.println("Number found: " + number);
            number_of_item= Integer.parseInt(number);
        }
        return number_of_item;
    }
    public static boolean click_add_to_basket_button_product_detail_page(WebDriver driver) {
        String new_arrival_product_summary_add_to_basket_button_xpath = locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_summary_add_to_basket_button");
        String new_arrival_product_summary_item_count_number_xpath = locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_summary_item_count_number");
        WebElement new_arrival_product_summary_add_to_basket_button_element = SubActivities.find_element_use_xpath(driver, new_arrival_product_summary_add_to_basket_button_xpath);
        if (new_arrival_product_summary_add_to_basket_button_element == null) {
            return false;
        }
        int get_number_of_item_in_cart_before_click=get_number_of_item_in_cart(driver);
        System.out.println("get_number_of_item_in_cart_before_click " + get_number_of_item_in_cart_before_click);
        new_arrival_product_summary_add_to_basket_button_element.click();
        int get_number_of_item_in_cart_after_click=get_number_of_item_in_cart(driver);
        System.out.println("get_number_of_item_in_cart_after_click " + get_number_of_item_in_cart_after_click);
        WebElement new_arrival_product_summary_item_count_number_element = SubActivities.find_element_use_xpath(driver, new_arrival_product_summary_item_count_number_xpath);
        if (new_arrival_product_summary_item_count_number_element == null) {
            return false;
        }
        String new_arrival_product_summary_item_count_number_value_attribute=new_arrival_product_summary_item_count_number_element.getAttribute("value");
        System.out.println("new_arrival_product_summary_item_count_number_value_attribute " + new_arrival_product_summary_item_count_number_value_attribute);
        if(get_number_of_item_in_cart_after_click!=get_number_of_item_in_cart_before_click+Integer.parseInt(new_arrival_product_summary_item_count_number_value_attribute)){
            return false;
        }
        return true;
    }
    public static boolean check_product_added_message_product_detail_page(WebDriver driver, String product_name) {
        String new_arrival_product_summary_add_to_basket_message_xpath = locators.arrival_add_to_basket_items_checkout_element.get("new_arrival_product_summary_add_to_basket_message");

        WebElement new_arrival_product_summary_add_to_basket_message_element = SubActivities.find_element_use_xpath(driver, new_arrival_product_summary_add_to_basket_message_xpath);
        if (new_arrival_product_summary_add_to_basket_message_element == null) {
            return false;
        }
        String new_arrival_product_summary_add_to_basket_message_text=new_arrival_product_summary_add_to_basket_message_element.getText().replaceAll("\\s+", " ").trim();;
        System.out.println("new_arrival_product_summary_add_to_basket_message_text " + new_arrival_product_summary_add_to_basket_message_text);
        if(!new_arrival_product_summary_add_to_basket_message_text.contains(product_name)){
            return false;
        }
        return true;
    }

    public static boolean click_item_detail_menu(WebDriver driver){
        String view_your_shopping_cart_link_xpath = locators.arrival_add_to_basket_items_checkout_element.get("view_your_shopping_cart_link");
        String shopping_cart_coupon_button_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_coupon_button");

        WebElement view_your_shopping_cart_link_element = SubActivities.find_element_use_xpath(driver, view_your_shopping_cart_link_xpath);
        if (view_your_shopping_cart_link_element == null) {
            return false;
        }
        view_your_shopping_cart_link_element.click();
        WebElement shopping_cart_coupon_button_element = SubActivities.find_element_use_xpath(driver, shopping_cart_coupon_button_xpath);
        if (shopping_cart_coupon_button_element == null) {
            return false;
        }
        return true;
    }

    public static String extractNumber(String str) {
        StringBuilder number = new StringBuilder();
        boolean foundDecimal = false;

        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                number.append(c);
            } else if (!foundDecimal && c == '.') {
                foundDecimal = true;
                break; // stop when decimal is found
            }
        }

        return number.toString();
    }

    public static boolean check_product_total_checkout_page(WebDriver driver, String product_name){
        String shopping_cart_product_price_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_product_price").replace("$(product_name)", product_name);
        String shopping_cart_product_quantity_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_product_quantity").replace("$(product_name)", product_name);
        String shopping_cart_product_total_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_product_total").replace("$(product_name)", product_name);
        int product_price=0;
        int product_total=0;

        WebElement shopping_cart_product_price_element = SubActivities.find_element_use_xpath(driver, shopping_cart_product_price_xpath);
        if (shopping_cart_product_price_element == null) {
            return false;
        }
        String shopping_cart_product_price_text= shopping_cart_product_price_element.getText();
        System.out.println("shopping_cart_product_price_text " + shopping_cart_product_price_text);
        product_price=Integer.parseInt(extractNumber(shopping_cart_product_price_text));
        System.out.println("product_price " + product_price);
        WebElement shopping_cart_product_quantity_element = SubActivities.find_element_use_xpath(driver, shopping_cart_product_quantity_xpath);
        if (shopping_cart_product_quantity_element == null) {
            return false;
        }
        String shopping_cart_product_quantity_value_attribute=shopping_cart_product_quantity_element.getAttribute("value");
        System.out.println("shopping_cart_product_quantity_value_attribute " + shopping_cart_product_quantity_value_attribute);
        WebElement shopping_cart_product_total_element = SubActivities.find_element_use_xpath(driver, shopping_cart_product_total_xpath);
        if (shopping_cart_product_total_element == null) {
            return false;
        }
        String shopping_cart_product_total_text= shopping_cart_product_total_element.getText();
        System.out.println("shopping_cart_product_total_text " + shopping_cart_product_total_text);
        product_total= Integer.parseInt(extractNumber(shopping_cart_product_total_text));
        System.out.println("product_total " + product_total);
        int actual_product_total=product_price*Integer.parseInt(shopping_cart_product_quantity_value_attribute);
        System.out.println("actual_product_total " + actual_product_total);
        if(actual_product_total!=product_total){
            return false;
        }
        return true;
    }

    public static boolean check_basket_total_checkout_page(WebDriver driver, String product_name){
        String shopping_cart_basket_sub_total_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_basket_sub_total").replace("$(product_name)", product_name);
        String shopping_cart_basket_tax_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_basket_tax").replace("$(product_name)", product_name);
        String shopping_cart_basket_total_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_basket_total").replace("$(product_name)", product_name);
        int product_price=0;
        int product_total_tax=0;
        int basket_total=0;

        WebElement shopping_cart_basket_sub_total_element = SubActivities.find_element_use_xpath(driver, shopping_cart_basket_sub_total_xpath);
        if (shopping_cart_basket_sub_total_element == null) {
            return false;
        }
        String shopping_cart_basket_sub_total_text= shopping_cart_basket_sub_total_element.getText();
        System.out.println("shopping_cart_basket_sub_total_text " + shopping_cart_basket_sub_total_text);
        product_price=Integer.parseInt(extractNumber(shopping_cart_basket_sub_total_text));
        System.out.println("product_price " + product_price);
        WebElement shopping_cart_basket_tax_element = SubActivities.find_element_use_xpath(driver, shopping_cart_basket_tax_xpath);
        if (shopping_cart_basket_tax_element == null) {
            return false;
        }
        String shopping_cart_product_total_tax_text= shopping_cart_basket_tax_element.getText();
        System.out.println("shopping_cart_product_total_tax_text " + shopping_cart_product_total_tax_text);
        product_total_tax= Integer.parseInt(extractNumber(shopping_cart_product_total_tax_text));
        System.out.println("product_total_tax " + product_total_tax);
        WebElement shopping_cart_basket_total_element = SubActivities.find_element_use_xpath(driver, shopping_cart_basket_total_xpath);
        if (shopping_cart_basket_total_element == null) {
            return false;
        }
        String shopping_cart_basket_basket_total_text= shopping_cart_basket_total_element.getText();
        System.out.println("shopping_cart_basket_basket_total_text " + shopping_cart_basket_basket_total_text);
        basket_total=Integer.parseInt(extractNumber(shopping_cart_basket_basket_total_text));
        System.out.println("basket_total " + basket_total);
        if(!(basket_total >product_price)){
            return false;
        }
        return true;
    }

    public static boolean remove_product_from_checkout_page(WebDriver driver, String product_name){
        String shopping_cart_remove_icon_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_remove_icon").replace("$(product_name)", product_name);
        String shopping_cart_remove_success_message_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_remove_success_message");


        WebElement shopping_cart_remove_icon_element = SubActivities.find_element_use_xpath(driver, shopping_cart_remove_icon_xpath);
        if (shopping_cart_remove_icon_element == null) {
            return false;
        }
        shopping_cart_remove_icon_element.click();
        WebElement shopping_cart_remove_success_message_element = SubActivities.find_element_use_xpath(driver, shopping_cart_remove_success_message_xpath);
        if (shopping_cart_remove_success_message_element == null) {
            return false;
        }
        String shopping_cart_remove_success_message_text=shopping_cart_remove_success_message_element.getText();
        System.out.println("shopping_cart_remove_success_message_text " + shopping_cart_remove_success_message_text);
        String expected_message= product_name + " removed.";
        System.out.println("expected_message " + expected_message);
        if(!shopping_cart_remove_success_message_text.contains(expected_message)){
            return false;
        }
        return true;
    }

    public static boolean update_basket_product_from_checkout_page(WebDriver driver, String product_name, String item_quantity) throws InterruptedException {
        String shopping_cart_product_quantity_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_product_quantity").replace("$(product_name)", product_name);
        String shopping_cart_update_cart_button_xpath = locators.arrival_add_to_basket_items_checkout_element.get("shopping_cart_update_cart_button");

        WebElement shopping_cart_product_quantity_element = SubActivities.find_element_use_xpath(driver, shopping_cart_product_quantity_xpath);
        if (shopping_cart_product_quantity_element == null) {
            return false;
        }
        int number_of_item_in_cart_before_click=get_number_of_item_in_cart(driver);
        System.out.println("number_of_item_in_cart_before_click " + number_of_item_in_cart_before_click);
        shopping_cart_product_quantity_element.clear();
        shopping_cart_product_quantity_element.sendKeys(item_quantity);
        WebElement shopping_cart_update_cart_button_xpath_element = SubActivities.find_element_use_xpath(driver, shopping_cart_update_cart_button_xpath);
        if (shopping_cart_update_cart_button_xpath_element == null) {
            return false;
        }
        shopping_cart_update_cart_button_xpath_element.click();
        Thread.sleep(2000);
        int number_of_item_in_cart_after_click=get_number_of_item_in_cart(driver);
        System.out.println("number_of_item_in_cart_after_click " + number_of_item_in_cart_after_click);
        if(number_of_item_in_cart_after_click!=(Integer.parseInt(item_quantity))){
            return false;
        }
        return true;
    }

    public static boolean click_proceed_to_checkout_from_checkout_page(WebDriver driver) throws InterruptedException {
        String proceed_to_checkout_button_xpath = locators.arrival_add_to_basket_items_checkout_element.get("proceed_to_checkout_button");
        String billing_details_heading_xpath = locators.arrival_add_to_basket_items_checkout_element.get("billing_details_heading");

        WebElement proceed_to_checkout_button_element = SubActivities.find_element_use_xpath(driver, proceed_to_checkout_button_xpath);
        if (proceed_to_checkout_button_element == null) {
            return false;
        }
        proceed_to_checkout_button_element.click();
        WebElement billing_details_heading_element = SubActivities.find_element_use_xpath(driver, billing_details_heading_xpath);
        if (billing_details_heading_element == null) {
            return false;
        }
        return true;
    }

}


