/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

angular.module('cashmachine').config(function( $routeProvider, $locationProvider ){
  $routeProvider.
      when('/card', {
        templateUrl : 'views/card.view.html',
        controller  : 'CardController',
        controllerAs: 'card'
      }).
      when('/pincode', {
        templateUrl : 'views/pincode.view.html',
        controller  : 'CardController',
        controllerAs: 'card'
      }).
      when('/operations', {
        templateUrl : 'views/operations.view.html'
      }).
      when('/reports', {
        templateUrl : 'views/reports.view.html',
        controller  : 'OperationsController',
        controllerAs: 'reports'
      }).
      when('/balance', {
        templateUrl : 'views/balance.view.html',
        controller  : 'OperationsController',
        controllerAs: 'balance'
      }).
      when('/withdraw', {
        templateUrl : 'views/withdraw.view.html',
        controller  : 'OperationsController',
        controllerAs: 'withdraw'
      }).
      otherwise({
        redirectTo: '/card'
      });
  
  $locationProvider.html5Mode(true);
});
