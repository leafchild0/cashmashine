/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */


(function(){
  'use strict';
  
  angular.module('cashmachine').controller('OperationsController', OperationsController);
  
  OperationsController.$inject = [ '$scope', 'toaster', 'cardsService' ];
  
  function OperationsController( $scope, toaster, cardsService ){
    var vm = this;
    vm.userCard = cardsService.customerCard;
    vm.currentDate = new Date();

    vm.getCard = function(){

      cardsService.cardInfo().get({ id: cardsService.customerCardId }).$promise.then(function( response ){
            cardsService.customerCard = response;
            vm.userCard = response;
            //$scope.$digest;
          },
          function( response ){
            toaster.error("Error " + response.status, "Cause: " + response.data);
          });
    };

    vm.getCard();

  }
  
})();
