/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

(function () {
    'use strict';

    angular
    .module('cashmachine')
     .service('cardsService', CardsService);

    CardsService.$inject = ['$resource', 'baseUrl'];


    /** @ngInject */
    function CardsService($resource, baseUrl) {

       this.customerCard = {};

       this.cardN = function() {
            return $resource(baseUrl + 'api/cards/number/:number', null, {});
       }

       this.pinCode = function() {
            return $resource(baseUrl + 'api/cards/pincode', null, {});
       }

       this.card = function() {
            return $resource(baseUrl + 'api/cards/:id', null, {});
       }

       this.cards = function() {
            return $resource(baseUrl + 'api/cards', null, {});
       }
    }
})();