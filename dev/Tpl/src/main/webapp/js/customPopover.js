angular.module("customAngPop.bootstrap", ["customAngPop.bootstrap.tooltip"]);


/**
 * The following features are still outstanding: animation as a
 * function, placement as a function, inside, support for more triggers than
 * just mouse enter/leave, html tooltips, and selector delegation.
 */
angular.module('customAngPop.bootstrap.tooltip', [ 'ui.bootstrap.position' ])

/**
 * The $iantooltip service creates tooltip- and popover-like directives as well as
 * houses global options for them.
 */
  .provider('$iantooltip', function () {
    // The default options tooltip and popover.
    var defaultOptions = {
      placement: 'top',
      animation: true,
      popupDelay: 0
    };

    // Default hide triggers for each show trigger
    var triggerMap = {
      'mouseenter': 'mouseleave',
      'click': 'click',
      'focus': 'blur'
    };

    // The options specified to the provider globally.
    var globalOptions = {};

    /**
     * `options({})` allows global configuration of all tooltips in the
     * application.
     *
     *   var app = angular.module( 'App', ['ui.bootstrap.tooltip'], function( $iantooltipProvider ) {
   *     // place tooltips left instead of top by default
   *     $iantooltipProvider.options( { placement: 'left' } );
   *   });
     */
    this.options = function (value) {
      angular.extend(globalOptions, value);
    };

    /**
     * This allows you to extend the set of trigger mappings available. E.g.:
     *
     *   $iantooltipProvider.setTriggers( 'openTrigger': 'closeTrigger' );
     */
    this.setTriggers = function setTriggers (triggers) {
      angular.extend(triggerMap, triggers);
    };

    /**
     * This is a helper function for translating camel-case to snake-case.
     */
    function snake_case (name) {
      var regexp = /[A-Z]/g;
      var separator = '-';
      return name.replace(regexp, function (letter, pos) {
        return (pos ? separator : '') + letter.toLowerCase();
      });
    }

    //noinspection JSValidateTypes
    /**
     * Returns the actual instance of the $iantooltip service.
     * TODO support multiple triggers
     */
    this.$get = [ '$compile', '$timeout', '$parse', '$document', '$position', '$interpolate', function ($compile, $timeout, $parse, $document, $position, $interpolate) {
      return function $iantooltip (type, prefix, defaultTriggerShow) {
        var options = angular.extend({}, defaultOptions, globalOptions);

        function setTriggers (trigger) {
          var show, hide;

          show = trigger || options.trigger || defaultTriggerShow;
          if (angular.isDefined(options.trigger)) {
            hide = triggerMap[options.trigger] || show;
          } else {
            hide = triggerMap[show] || show;
          }

          return {
            show: show,
            hide: hide
          };
        }

        var directiveName = snake_case(type);
        var triggers = setTriggers(undefined);

        var startSym = $interpolate.startSymbol();
        var endSym = $interpolate.endSymbol();
        var template =
          '<' + directiveName + '-popup ' +
            'title="iantooltipTitle" ' +
            'content="' + startSym + 'tt_content' + endSym + '" ' +
            'placement="' + startSym + 'tt_placement' + endSym + '" ' +
            'animation="tt_animation()" ' +
            'is-open="tt_isOpen"' +
            '>' +
            '</' + directiveName + '-popup>';

        return {
          restrict: 'EA',
          scope: {iantooltipTitle: '='},
          transclude:true,
          template:'<div  ng-transclude><div>',
          link: function link (scope, element, attrs) {
            var tooltip = $compile(template)(scope);
            var isTooltipExist = false;
            var transitionTimeout;
            var popupTimeout;
            var $body;
            var appendToBody = angular.isDefined(options.appendToBody) ? options.appendToBody : false;

            scope.hide = function () {
              hide();
            }
            // By default, the tooltip is not open.
            // TODO add ability to start tooltip opened
            scope.tt_isOpen = false;

            function toggleTooltipBind () {
              if (!scope.tt_isOpen) {
                showTooltipBind();
              } else {
                hideTooltipBind();
              }
            }

            // Show the tooltip with delay if specified, otherwise show it immediately
            function showTooltipBind () {
              if (scope.tt_popupDelay) {
                popupTimeout = $timeout(show, scope.tt_popupDelay);
              } else {
                scope.$apply(show);
              }
            }

            function hideTooltipBind () {
              scope.$apply(function () {
                hide();
              });
            }

            // Show the tooltip popup element.
            function show () {
              var position,
                ttWidth,
                ttHeight,
                ttPosition;

              // Don't show empty tooltips.
              if (!scope.tt_content) {
                return;
              }

              // If there is a pending remove transition, we must cancel it, lest the
              // tooltip be mysteriously removed.
              if (transitionTimeout) {
                $timeout.cancel(transitionTimeout);
              }

              // Set the initial positioning.
              tooltip.css({ top: 0, left: 0, display: 'block' });

              if (!isTooltipExist) {
                if ( appendToBody ) {
                    $body = $body || $document.find( 'body' );
                    $body.append( tooltip );
                } else {
                    element.after( tooltip );
                }
              } 

              // Get the position of the directive element.
              position = options.appendToBody ? $position.offset(element) : $position.position(element);

              // Get the height and width of the tooltip so we can center it.
              ttWidth = tooltip.prop('offsetWidth');
              ttHeight = tooltip.prop('offsetHeight');

              // Calculate the tooltip's top and left coordinates to center it with
              // this directive.
              switch (scope.tt_placement) {
                case 'mouse':
                  var mousePos = $position.mouse();
                  ttPosition = {
                    top: mousePos.y,
                    left: mousePos.x
                  };
                  break;
                case 'right':
                  ttPosition = {
                    top: position.top + position.height / 2 - ttHeight / 2,
                    left: position.left + position.width
                  };
                  break;
                case 'bottom':
                  ttPosition = {
                    top: position.top + position.height,
                    left: position.left + position.width / 2 - ttWidth / 2
                  };
                  break;
                case 'left':
                  ttPosition = {
                    top: position.top + position.height / 2 - ttHeight / 2,
                    left: position.left - ttWidth
                  };
                  break;
                default:
                  ttPosition = {
                    top: position.top - ttHeight,
                    left: position.left + position.width / 2 - ttWidth / 2
                  };
                  break;
              }

              ttPosition.top += 'px';
              ttPosition.left += 'px';

              // Now set the calculated positioning.
              tooltip.css(ttPosition);

              // And show the tooltip.
              scope.tt_isOpen = true;
            }

            // Hide the tooltip popup element.
            function hide () {
              // First things first: we don't show it anymore.
              scope.tt_isOpen = false;

              //if tooltip is going to be shown after delay, we must cancel this
              $timeout.cancel(popupTimeout);
        
            }

            /**
             * Observe the relevant attributes.
             */

            attrs.$observe(prefix + 'Mediaid', function (val) {
              scope.tt_mediaid = val;
            });

            attrs.$observe(type, function (val) {
              scope.tt_content = val;
            });

            // attrs.$observe(prefix + 'Title', function (val) {
            //   scope.tt_title = val;
            // });

            attrs.$observe(prefix + 'Placement', function (val) {
              scope.tt_placement = angular.isDefined(val) ? val : options.placement;
            });

            attrs.$observe(prefix + 'Animation', function (val) {
              scope.tt_animation = angular.isDefined(val) ? $parse(val) : function () {
                return options.animation;
              };
            });

            attrs.$observe(prefix + 'PopupDelay', function (val) {
              var delay = parseInt(val, 10);
              scope.tt_popupDelay = !isNaN(delay) ? delay : options.popupDelay;
            });

            attrs.$observe(prefix + 'Trigger', function (val) {
              element.unbind(triggers.show);
              element.unbind(triggers.hide);

              triggers = setTriggers(val);

              if (triggers.show === triggers.hide) {
                element.bind(triggers.show, toggleTooltipBind);
              } else {
                element.bind(triggers.show, showTooltipBind);
                element.bind(triggers.hide, hideTooltipBind);
              }
            });

            attrs.$observe(prefix + 'AppendToBody', function (val) {
              appendToBody = angular.isDefined(val) ? $parse(val)(scope) : appendToBody;
            });

            // if a tooltip is attached to <body> we need to remove it on
            // location change as its parent scope will probably not be destroyed
            // by the change.
            if (appendToBody) {
              scope.$on('$locationChangeSuccess', function closeTooltipOnLocationChangeSuccess () {
                if (scope.tt_isOpen) {
                  hide();
                }
              });
            }

            // Make sure tooltip is destroyed and removed.
            scope.$on('$destroy', function onDestroyTooltip () {
              if (scope.tt_isOpen) {
                hide();
              } else {
                tooltip.remove();
              }
            });
          }
        };
      };
    }];
  })

  .directive('iantooltipPopup', function () {
    return {
      restrict: 'E',
      replace: true,
      scope: { mediaid: '@', title: '=', content: '@', placement: '@', animation: '&', isOpen: '=' },
      templateUrl: 'app/views/registerPopover.html',
      link: function (scope, element, attrs) {
        //scope.showForm = true;
        scope.onDoneClick = function () {
        	if(scope.newUser.pass1 == scope.newUser.pass2)
        		{
        			scope.newUser = {};
        			scope.registerForm.$setPristine();
        			scope.$parent.hide();
        		}
        	
          // scope.$parent.$parent.item.title = scope.title;
          // scope.$parent.hide();
        };
      }
    };
  })

  .directive('iantooltip', [ '$iantooltip', function ($iantooltip) {
    return $iantooltip('iantooltip', 'iantooltip', 'click');
  }]);