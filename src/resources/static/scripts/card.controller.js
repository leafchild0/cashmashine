/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */


( function () {
    'use strict';

    angular.module( 'cashmachine' )
            .controller( 'CardController', CardController );

    CardController.$inject = ['$location', 'cardsService'];

    function CardController($location, cardsService) {
        var vm = this;
        vm.cardNumber = '';
        vm.cardPin = '';
        vm.cardWithoutHygh = 0;
        vm.customerCard = {};

        vm.keys = [1, 2, 3, 4, 5, 6, 7, 8, 9];

        vm.onKeyPressed = function(data) {
            if (data == '<') {
                vm.cardNumber = vm.cardNumber.slice(0, vm.cardNumber.length - 1);
                vm.cardWithoutHygh -= 1;
                return;
            } else {
                vm.cardNumber += data;
                vm.cardWithoutHygh += 1;
                if(vm.cardWithoutHygh === 4) {
                   vm.cardNumber += '-';
                   vm.cardWithoutHygh = 0;
                }
            }


        }

        vm.checkCard = function() {
            //If card is exists in the DB, go to the pin check page, otherwise show error

            cardsService.card().get({id: vm.cardNumber.split('-').join('')})
                      .$promise.then(function (response) {
                        //Remove from UI
                        cardsService.customerCard = response;
                        $location.path('pincode');
                      },
                      function (response) {
                        vm.message = "Error: " + response.status + " " + response.statusText;
                        $location.path('error');
                      });
        }

        vm.checkPin = function() {
            //If card is exists in the DB, go to the pin check page, otherwise show error

            cardsService.pinCode().save({id: cardsService.customerCard, pinCode: vm.cardPin})
                      .$promise.then(function (response) {
                        //Remove from UI
                        $location.path('operations');
                      },
                      function (response) {
                        vm.message = "Error: " + response.status + " " + response.statusText;
                        $location.path('error');
                      });
        }

    }

} )();