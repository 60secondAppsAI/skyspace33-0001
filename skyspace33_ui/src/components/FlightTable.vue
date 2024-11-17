
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Flights</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalFlights = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalFlights">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Flight</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="FlightId" type="text" placeholder="Enter FlightId" v-model="flightToAdd.flightId"></base-input>
  <base-input label="FlightNumber" type="text" placeholder="Enter FlightNumber" v-model="flightToAdd.flightNumber"></base-input>
  <base-input label="DepartureCity" type="text" placeholder="Enter DepartureCity" v-model="flightToAdd.departureCity"></base-input>
  <base-input label="ArrivalCity" type="text" placeholder="Enter ArrivalCity" v-model="flightToAdd.arrivalCity"></base-input>
  <base-input label="DepartureTime" type="text" placeholder="Enter DepartureTime" v-model="flightToAdd.departureTime"></base-input>
  <base-input label="ArrivalTime" type="text" placeholder="Enter ArrivalTime" v-model="flightToAdd.arrivalTime"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="flights" :row-key="record => record.FlightId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <FlightPictureView :flights="flights" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import FlightService from "../services/FlightService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import FlightPictureView from './FlightPictureView.vue';


const flightsColumns = [
  "flightId",
  "year",
  "date",
  "competitionId",
  "flightId"
]

export default {
  props: {
    flights: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    FlightPictureView
  },

  data() {
    return {
      modalFlights: false,
        isTableView: true,

      columns: [
        {
          title: 'Flight Id',
		dataIndex: 'flightId',
          visible: true,
          scopedSlots: { customRender: 'flightId' },
          sorter: true
          //sorter: (a, b) => a.flightId - b.flightId,
          //sorter: (a, b) => a.flightId.localeCompare(b.flightId),
        },
        {
          title: 'Flight Number',
		dataIndex: 'flightNumber',
          visible: true,
          scopedSlots: { customRender: 'flightNumber' },
          sorter: true
          //sorter: (a, b) => a.flightNumber - b.flightNumber,
          //sorter: (a, b) => a.flightNumber.localeCompare(b.flightNumber),
        },
        {
          title: 'Departure City',
		dataIndex: 'departureCity',
          visible: true,
          scopedSlots: { customRender: 'departureCity' },
          sorter: true
          //sorter: (a, b) => a.departureCity - b.departureCity,
          //sorter: (a, b) => a.departureCity.localeCompare(b.departureCity),
        },
        {
          title: 'Arrival City',
		dataIndex: 'arrivalCity',
          visible: true,
          scopedSlots: { customRender: 'arrivalCity' },
          sorter: true
          //sorter: (a, b) => a.arrivalCity - b.arrivalCity,
          //sorter: (a, b) => a.arrivalCity.localeCompare(b.arrivalCity),
        },
        {
          title: 'Departure Time',
		dataIndex: 'departureTime',
          visible: true,
          scopedSlots: { customRender: 'departureTime' },
          sorter: true
          //sorter: (a, b) => a.departureTime - b.departureTime,
          //sorter: (a, b) => a.departureTime.localeCompare(b.departureTime),
        },
        {
          title: 'Arrival Time',
		dataIndex: 'arrivalTime',
          visible: true,
          scopedSlots: { customRender: 'arrivalTime' },
          sorter: true
          //sorter: (a, b) => a.arrivalTime - b.arrivalTime,
          //sorter: (a, b) => a.arrivalTime.localeCompare(b.arrivalTime),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} flights`,
      },

      flights: [],
      flightToAdd: {},

      flightsTable: {
        columns: [...flightsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'flightId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderFlightsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let flightsTableData = [];
      for (let i = 0; i < this.flights.length; i++) {
        flightsTableData.push({
          id: i,
          flightId: this.flights[i].flightId,
          year: this.flights[i].year,
          date: this.flights[i].date,
          competitionId: this.flights[i].competitionId,
          flightId: this.flights[i].flightId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-flights',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToAirplaneDetail(id) {
      this.$router.push({ name: 'AirplaneDetail', params: { airplaneId: id.toString() }})
    },
    routingToAirlineDetail(id) {
      this.$router.push({ name: 'AirlineDetail', params: { airlineId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToBaggageDetail(id) {
      this.$router.push({ name: 'BaggageDetail', params: { baggageId: id.toString() }})
    },
    routingToDestinationDetail(id) {
      this.$router.push({ name: 'DestinationDetail', params: { destinationId: id.toString() }})
    },
    routingToCityDetail(id) {
      this.$router.push({ name: 'CityDetail', params: { cityId: id.toString() }})
    },
    routingToRouteDetail(id) {
      this.$router.push({ name: 'RouteDetail', params: { routeId: id.toString() }})
    },
    routingToCrewDetail(id) {
      this.$router.push({ name: 'CrewDetail', params: { crewId: id.toString() }})
    },
    routingToPilotDetail(id) {
      this.$router.push({ name: 'PilotDetail', params: { pilotId: id.toString() }})
    },
    routingToRuleDetail(id) {
      this.$router.push({ name: 'RuleDetail', params: { ruleId: id.toString() }})
    },
    routingToCheckinDetail(id) {
      this.$router.push({ name: 'CheckinDetail', params: { checkinId: id.toString() }})
    },
    routingToLoyaltyProgramDetail(id) {
      this.$router.push({ name: 'LoyaltyProgramDetail', params: { loyaltyProgramId: id.toString() }})
    },
    routingToMembershipDetail(id) {
      this.$router.push({ name: 'MembershipDetail', params: { membershipId: id.toString() }})
    },
    routingToReviewDetail(id) {
      this.$router.push({ name: 'ReviewDetail', params: { reviewId: id.toString() }})
    },
    routingToNotificationDetail(id) {
      this.$router.push({ name: 'NotificationDetail', params: { notificationId: id.toString() }})
    },
    routingToPromotionDetail(id) {
      this.$router.push({ name: 'PromotionDetail', params: { promotionId: id.toString() }})
    },
    routingToFeedbackDetail(id) {
      this.$router.push({ name: 'FeedbackDetail', params: { feedbackId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForFlightsChanged', this.searchQuery);
		//this.renderFlightsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalFlights = false;

      const currentDate = new Date().getTime();
      this.flightToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.flightToAdd);
      console.log(jsonData);
      
      const res = await FlightService.addFlight(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderFlightsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
