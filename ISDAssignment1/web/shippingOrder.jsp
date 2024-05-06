<%-- 
    Document   : shippingOrder
    Created on : 23 Apr. 2024, 12:40:19 pm
    Author     : lorinchanel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Barlow:400,600">
        
        <title>Order</title>
        
    </head>
    
    <body>
        
        
        <div class="shipping-container">
            <h2>Shipping Address</h2>
            
            
            <form method="post">
                
                <div class="shipping-details-container">
                <label for="streetAddress">Street Address: </label><br>
                <input type="text" id = "streetAddress" name = "streetAddress">
                
                <table>
                            <tr>
                                <td>City: </td>
                                <td>State/Territory: </td>
                                <td>Postcode: </td>
                            </tr>
                            <tr>
                                <td><input type= "text" id = "city" name = "city"></td>
                                <td>
                                    <div class="country-dropdown">
                                    <select id="suburb" name="suburb">
                                        <option value="" selected> choose one</option>
                                        <option value="act">Australian Capital Territory</option>
                                        <option value="nsw">New South Wales</option>
                                        <option value="nt">Northern Territory</option>
                                        <option value="qld">Queensland</option>
                                        <option value="sa">South Australia</option>
                                        <option value="tas">Tasmania</option>
                                        <option value="vic">Victoria</option>
                                        <option value="wa">Western Australia</option>
                                    </select>
                                    </div>
                                </td>
                                <td><input type= "text" id = "postcode" name = "postcode"></td>
                            </tr>
                        </table>
                
                <label for="shipmentMethod">Delivery Method: </label><br>
                <select name="suburb">
                    <option value="" selected> Choose Your Delivery Type</option>
                    <option value="Standard">Standard</option>
                    <option value="Express">Express</option>
                </select>
            
                
               </div> 

                <button class="submit-button" type="submit" name="orderSubmit" value="true"> Submit </button>
                
        </form>
        </div>
           
        <!--
       <div class="flexbox">
        <div class="left-container">
            
                
                
                <div class="shipping-details-container">
                
                    <label for="streetAddress">Street Address: </label><br>
                    <input type="text" id = "streetAddress" name = "streetAddress">

                    <div class="shipping-details-columns">
                        <table>
                            <tr>
                                <td>City: </td>
                                <td>State/Territory: </td>
                                <td>Postcode: </td>
                            </tr>
                            <tr>
                                <td><input type= "text" id = "city" name = "city"></td>
                                <td>
                                    <div class="country-dropdown">
                                    <select name="suburb">
                                        <option value="" selected> choose one</option>
                                        <option value="act">Australian Capital Territory</option>
                                        <option value="nsw">New South Wales</option>
                                        <option value="nt">Northern Territory</option>
                                        <option value="qld">Queensland</option>
                                        <option value="sa">South Australia</option>
                                        <option value="tas">Tasmania</option>
                                        <option value="vic">Victoria</option>
                                        <option value="wa">Western Australia</option>
                                    </select>
                                    </div>
                                </td>
                                <td><input type= "text" id = "postcode" name = "postcode"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                

                
                
                
                
            </form>
           
                
        </div>
        
        
        <div class="right-container"> 
                    <h1>wee</h1>
            </div>
        </div>-->
        
    </body>
</html>
