<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Please enter a pincode</title>
    <script type="text/javascript">
        function keyboardAction(e) {
            if (e.target.tagName == "INPUT") {
                var button = e.target;
                var pincodeField = document.getElementById("pincode");
                pincodeField.value += button.id;
            }
        }
    </script>
</head>
<body>
<form name="cc" action="pincode" method="get">

    <div align="center" style="margin: 5px 5px 15px 5px">
        <label for="pincode">Please enter your pincode</label>
        <input id="pincode" type="password" size="24" maxlength="20" name="pincode"/>
    </div>

    <div id="virtualKeyboard" onclick="keyboardAction(event)">
        <table id="tableKeyboard" align="center">
            <tr>
                <td><input type="button" value="1" id="1"></td>
                <td><input type="button" value="2" id="2"></td>
                <td><input type="button" value="3" id="3"></td>
            </tr>
            <tr>
                <td><input type="button" value="4" id="4"></td>
                <td><input type="button" value="5" id="5"></td>
                <td><input type="button" value="6" id="6"></td>
            <tr>
                <td><input type="button" value="7" id="7"></td>
                <td><input type="button" value="8" id="8"></td>
                <td><input type="button" value="9" id="9"></td>
                <td><input type="button" value="0" id="0"></td>
            </tr>
        </table>
    </div>

    <div align="center" class="controls" style="margin-top: 15px">
        <input type="reset" value="Clean"/>
        <input type="submit" value="OK"/>
        <input type="button" value="Exit"/>
    </div>
</form>
</body>
</html>