/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

(function () {
    'use strict';

    angular
    .module('cashmachine')
     .service('operationsService', OperationsService);

    OperationsService.$inject = ['$resource', 'baseUrl'];


    /** @ngInject */
    function OperationsService($resource, baseUrl) {

       this.transaction = function() {
            return $resource(baseUrl + 'api/transactions/:id', null, {});
       }

       this.transactions = function() {
            return $resource(baseUrl + 'api/transactions', null, {});
       }
    }
})();