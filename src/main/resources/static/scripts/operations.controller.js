/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */


(function(){
  'use strict';
  
  angular.module('cashmachine').controller('OperationsController', OperationsController);
  
  OperationsController.$inject = [ '$scope', '$location', 'toaster', 'cardsService' ];
  
  function OperationsController( $scope, $location, toaster, cardsService ){
    var vm = this;
    vm.userCard = cardsService.customerCard;
    vm.currentDate = new Date();
    vm.amount = '';
    vm.keys = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ];

    vm.onKeyPressed = function( data ){
          if( data == '<' ){
            vm.amount = vm.amount.slice(0, vm.amount.length - 1);
          } else {
            vm.amount += data;
          }
        };

    vm.getCard = function(){

      cardsService.cardInfo().get({ id: cardsService.customerCardId })
      .$promise.then(function( response ){
            cardsService.customerCard = response;
            vm.userCard = response;
            //$scope.$digest;
          },
          function( response ){
            toaster.error("Error " + response.status, "Cause: " + response.data);
          });
    };

    vm.makeWithdraw = function(){

      cardsService.withDraw().save({ id: cardsService.customerCardId }, { amount: vm.amount })
      .$promise.then(function( response ){
            cardsService.customerCard = response;
            cardsService.customerCard.lastWithdraw = vm.amount;
            $location.path('reports');

          },
          function( response ){
            toaster.error("Error " + response.status, "Cause: " + response.data);
          });
    };

  }
  
})();
