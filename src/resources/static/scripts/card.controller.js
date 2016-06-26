/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */


(function(){
  'use strict';

  angular.module('cashmachine').controller('CardController', CardController);

  CardController.$inject = [ '$location', 'toaster', 'cardsService' ];

  function CardController( $location, toaster, cardsService ){
    var vm = this;
    vm.cardNumber = '';
    vm.cardPin = '';
    vm.tries = 0;
    vm.cardWithoutHygh = 0;
    vm.customerCard = {};

    vm.keys = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ];

    vm.onCardKeyPressed = function( data ){
      if( data == '<' ){
        vm.cardNumber = vm.cardNumber.slice(0, vm.cardNumber.length - 1);
        vm.cardWithoutHygh -= 1;
      } else {
        vm.cardNumber += data;
        vm.cardWithoutHygh += 1;
        if( vm.cardWithoutHygh === 4 ){
          vm.cardNumber += '-';
          vm.cardWithoutHygh = 0;
        }
      }
    };
    
    vm.onPinKeyPressed = function( data ){
      if( data == '<' ){
        vm.cardPin = vm.cardPin.slice(0, vm.cardPin.length - 1);
      } else {
        vm.cardPin += data;
      }
    };

    vm.checkCard = function(){
      //If card is exists in the DB, go to the pin check page, otherwise show error
      var card = vm.cardNumber.split('-').join('');

      cardsService.card().get({ id: card }).$promise.then(function( response ){
            //Remove from UI
            cardsService.customerCardId = card;
            $location.path('pincode');
          },
          function( response ){
            toaster.error("Error " + response.status, "Cause: " + response.data);
          });
    };

    vm.checkPin = function(){
      //If card is exists in the DB, go to the pin check page, otherwise show error
      var isLocked = vm.tries === 3;

      cardsService.pinCode().save({
        id: cardsService.customerCardId, pinCode: vm.cardPin, isLocked: isLocked
      }).$promise.then(
          function( response ){
            //Remove from UI
            $location.path('operations');
          },
          function( response ){
            toaster.error("Error " + response.status, "Cause: " + response.data);
            vm.tries += 1;
            vm.cardPin = '';
          });
    }

  }

})();