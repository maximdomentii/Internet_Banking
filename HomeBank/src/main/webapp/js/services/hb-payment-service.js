'use strict';

angular.module('homeBankApp').service('HBPaymentService',
    function ItemService($resource) {
        return $resource(
            'rest/payments/:id',
            { id: '@_id' },
            {
            	internalPayment: {
                    method: 'POST',
                    url: 'rest/payments/internalPayment',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                currentToSavingPayment: {
                    method: 'POST',
                    url: 'rest/payments/currentToSavingPayment',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                savingToCurrentPayment: {
                    method: 'POST',
                    url: 'rest/payments/savingToCurrentPayment',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                currentToCreditPayment: {
                    method: 'POST',
                    url: 'rest/payments/currentToCreditPayment',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                creditToCurrentPayment: {
                    method: 'POST',
                    url: 'rest/payments/creditToCurrentPayment',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
            });
    });
