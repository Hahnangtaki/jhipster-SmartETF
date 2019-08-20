import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/parameter-global">
      Parameter Global
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/tipe-efek">
      Tipe Efek
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/nasabah">
      Nasabah
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/efek">
      Efek
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/broker">
      Broker
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/bank-custodi">
      Bank Custodi
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/idx-holiday">
      Idx Holiday
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/harga-penutupan">
      Harga Penutupan
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/hair-cut">
      Hair Cut
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pengajuan-gadai-efek-header">
      Pengajuan Gadai Efek Header
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/pengajuan-gadai-efek-dtl">
      Pengajuan Gadai Efek Dtl
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/transaksi-gadai-efek-header">
      Transaksi Gadai Efek Header
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/eksekusi-header">
      Eksekusi Header
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/eksekusi-dtl">
      Eksekusi Dtl
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/eksekusi-summary">
      Eksekusi Summary
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
