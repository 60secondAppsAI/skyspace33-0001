<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <passenger-table
            v-if="passengers && passengers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:passengers="passengers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-passengers="getAllPassengers"
             >

            </passenger-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PassengerTable from "@/components/PassengerTable";
import PassengerService from "../services/PassengerService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PassengerTable,
  },
  data() {
    return {
      passengers: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPassengers(sortBy='passengerId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PassengerService.getAllPassengers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.passengers.length) {
					this.passengers = response.data.passengers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching passengers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching passenger details:", error);
      }
    },
  },
  mounted() {
    this.getAllPassengers();
  },
  created() {
    this.$root.$on('searchQueryForPassengersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPassengers();
    })
  }
};
</script>
<style></style>
