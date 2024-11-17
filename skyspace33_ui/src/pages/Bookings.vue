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
            <booking-table
            v-if="bookings && bookings.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:bookings="bookings"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-bookings="getAllBookings"
             >

            </booking-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BookingTable from "@/components/BookingTable";
import BookingService from "../services/BookingService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BookingTable,
  },
  data() {
    return {
      bookings: [],
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
    async getAllBookings(sortBy='bookingId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BookingService.getAllBookings(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.bookings.length) {
					this.bookings = response.data.bookings;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching bookings:", error);
        }
        
      } catch (error) {
        console.error("Error fetching booking details:", error);
      }
    },
  },
  mounted() {
    this.getAllBookings();
  },
  created() {
    this.$root.$on('searchQueryForBookingsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBookings();
    })
  }
};
</script>
<style></style>
