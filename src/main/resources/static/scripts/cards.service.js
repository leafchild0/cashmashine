/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

(function(){
  'use strict';
  
  angular.module('cashmachine').service('cardsService', CardsService);
  
  CardsService.$inject = [ '$resource', 'baseUrl' ];

  /** @ngInject */
  function CardsService( $resource, baseUrl ){
    
    this.customerCard = {};
    this.customerCardId = {};
    this.lastWithdraw = {};

    this.pinCode = function(){
      return $resource(baseUrl + 'api/cards/pincode', null, {});
    };
    
    this.card = function(){
      return $resource(baseUrl + 'api/cards/:id', null, {});
    };
    this.cards = function(){
      return $resource(baseUrl + 'api/cards', null, {});
    };
    
    this.cardInfo = function(){
      return $resource(baseUrl + 'api/cards/:id/balance', null, {});
    };

    this.withDraw = function(){
      return $resource(baseUrl + 'api/cards/:id/withdraw', null, {});
    };
  }
})();