CHCombust
=====

CommandHelper extension which adds entity_combust event.

## Events

#### entity_combust

Fires when an entity starts combusting.
* `id` Id of the combusting entity.
* `combuster_type` Type of the combuster. entity, block or other.
* `combuster` entity id that caused the combustion or the block item:data
* `location` The location array of where the combustion took place.
* `duration` The length is seconds for the combustion.

The combuster will be null whth the combustion_type is `other` or when the combuster cannot be found.
