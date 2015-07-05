<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cash Machine</title>
    <script type="text/javascript">
        function keyboardAction(e) {
            if (e.target.tagName == "INPUT") {
                var button = e.target;
                var ccField = document.getElementById("cardNumber");
                ccField.value += button.id;
            }
        }

        function validateCardNumber(inputTxt) {
            var cardno = /^([0-9]{16})$/;
            if (inputTxt.value.match(cardno)) {
                return true;
            }
            else {
                alert("Not a valid credit card");
                event.preventDefault();
                return false;
            }
        }
    </script>
</head>
<body>
<form name="cc" action="login" method="get" onsubmit="validateCardNumber(forms.cc.cardNumber)">

    <div align="center" style="margin: 5px 5px 15px 5px">
        <label for="cardNumber">Please enter your card number</label>
        <input id="cardNumber" type="text" size="24" maxlength="20" name="cardNumber" readonly/>
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
    </div>
</form>
</body>
</html>