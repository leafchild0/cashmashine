/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

angular.module('cashmachine', ['ngResource', 'ngRoute','credit-cards', 'ngKeypad', 'toaster'])
    .constant('baseUrl', 'http://localhost:8080/');
